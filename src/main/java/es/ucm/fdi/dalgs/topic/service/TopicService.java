package es.ucm.fdi.dalgs.topic.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.prefs.CsvPreference;

import es.ucm.fdi.dalgs.acl.service.AclObjectService;
import es.ucm.fdi.dalgs.classes.ResultClass;
import es.ucm.fdi.dalgs.classes.UploadForm;
import es.ucm.fdi.dalgs.domain.Module;
import es.ucm.fdi.dalgs.domain.Topic;
import es.ucm.fdi.dalgs.module.service.ModuleService;
import es.ucm.fdi.dalgs.subject.service.SubjectService;
import es.ucm.fdi.dalgs.topic.repository.TopicRepository;

@Service
public class TopicService {
	@Autowired
	private TopicRepository daoTopic;

	@Autowired
	private ModuleService serviceModule;

	@Autowired
	private SubjectService serviceSubject;
	
	@Autowired
	private AclObjectService manageAclService;
	
	@Autowired
	private MessageSource messageSource;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional(readOnly=false)	
	public ResultClass<Topic> addTopic(Topic topic, Long id_module, Locale locale) {

		boolean success = false;
		
		Topic topicExists = daoTopic.existByCode(topic.getInfo().getCode(), id_module);
		ResultClass<Topic> result = new ResultClass<>();

		if( topicExists != null){
			result.setHasErrors(true);
			Collection<String> errors = new ArrayList<String>();
			errors.add(messageSource.getMessage("error.Code", null, locale));

			if (topicExists.getIsDeleted()){
				result.setElementDeleted(true);
				errors.add(messageSource.getMessage("error.deleted", null, locale));
				result.setSingleElement(topicExists);
			}
			else result.setSingleElement(topic);
			result.setErrorsList(errors);
		}
		else{
			topic.setModule(serviceModule.getModule(id_module).getSingleElement());
			success = daoTopic.addTopic(topic);
			
			if(success){
				topicExists = daoTopic.existByCode(topic.getInfo().getCode(), id_module);
				success = manageAclService.addACLToObject(topicExists.getId(), topicExists.getClass().getName());
				if (success) result.setSingleElement(topic);
			
			}else{
				throw new IllegalArgumentException(	"Cannot create ACL. Object not set.");

			}
		}
		return result;		
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional(readOnly=true)
	public ResultClass<Topic> getAll() {
		ResultClass<Topic> result = new ResultClass<>();
		result.addAll(daoTopic.getAll());
		return result;
	}

	
	@PreAuthorize("hasPermission(#topic, 'WRITE') or hasPermission(#topic, 'ADMINISTRATION')")
	@Transactional(readOnly=false)
	public ResultClass<Boolean> modifyTopic(Topic topic, Long id_topic, Long id_module, Locale locale) {
		ResultClass<Boolean> result = new ResultClass<>();

		Topic modifyTopic = daoTopic.getTopic(id_topic);

		Topic topicExists = daoTopic.existByCode(topic.getInfo().getCode(), id_module);

		if(!topic.getInfo().getCode().equalsIgnoreCase(modifyTopic.getInfo().getCode()) && 
				topicExists != null){
			result.setHasErrors(true);
			Collection<String> errors = new ArrayList<>();
			errors.add(messageSource.getMessage("error.newCode", null, locale));

			if (topicExists.getIsDeleted()){
				result.setElementDeleted(true);
				errors.add(messageSource.getMessage("error.deleted", null, locale));

			}
			result.setErrorsList(errors);
			result.setSingleElement(false);
		}
		else{
			modifyTopic.setInfo(topic.getInfo());
			boolean r = daoTopic.saveTopic(modifyTopic);
			if (r) 
				result.setSingleElement(true);
		}
		return result;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional(readOnly=true)
	public ResultClass<Topic> getTopic(Long id) {
		ResultClass<Topic> result = new ResultClass<Topic>();
		result.setSingleElement(daoTopic.getTopic(id));
		return result;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	@Transactional(readOnly=false)
	public ResultClass<Boolean> deleteTopic(Topic topic) {
		ResultClass<Boolean> result = new ResultClass<>();
		Collection<Topic> topics = new ArrayList<>();
		topics.add(topic);
		if (serviceSubject.deleteSubjectsForTopic(topics).getSingleElement()){
			result.setSingleElement(daoTopic.deleteTopic(topic));
			return result;
		}
		result.setSingleElement(false);

		return result;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional(readOnly=true)
	public ResultClass<Topic> getTopicAll(Long id_topic, Boolean show) {
		ResultClass<Topic> result = new ResultClass<Topic>();
		Topic p = daoTopic.getTopic(id_topic);
		p.setSubjects(serviceSubject.getSubjectsForTopic(id_topic, show));
		result.setSingleElement(p);
		return result;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional(readOnly=true)
	public ResultClass<Topic> getTopicsForModule(Long id, Boolean show) {
		ResultClass<Topic> result = new ResultClass<>();
		result.addAll(daoTopic.getTopicsForModule(id, show));
		return result;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@Transactional(readOnly=false)
	public ResultClass<Boolean> deleteTopicsForModules(Collection<Module> modules) {
		ResultClass<Boolean> result = new ResultClass<Boolean>();
		Collection<Topic> topics = daoTopic.getTopicsForModules(modules);

		if(serviceSubject.deleteSubjectsForTopic(topics).getSingleElement()){
			result.setSingleElement(daoTopic.deleteTopicsForModules(modules));
			return result;
		}
		result.setSingleElement(false);
		return result;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional(readOnly=false)
	public ResultClass<Boolean> deleteTopicsForModule(Module module) {
		ResultClass<Boolean> result = new ResultClass<Boolean>();
		if(!module.getTopics().isEmpty())
			if(serviceSubject.deleteSubjectsForTopic(module.getTopics()).getSingleElement()){
				result.setSingleElement(daoTopic.deleteTopicsForModule(module));
				return result;
			}
			else result.setSingleElement(false);
		else result.setSingleElement(true);
		
		return result;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional(readOnly = false)
	public ResultClass<Topic> unDeleteTopic(Topic topic, Long id_module, Locale locale) {
		Topic t = daoTopic.existByCode(topic.getInfo().getCode(), id_module);
		ResultClass<Topic> result = new ResultClass<>();
		if(t == null){
			result.setHasErrors(true);
			Collection<String> errors = new ArrayList<String>();
			errors.add(messageSource.getMessage("error.ElementNoExists", null, locale));
			result.setErrorsList(errors);

		}
		else{
			if(!t.getIsDeleted()){
				Collection<String> errors = new ArrayList<String>();
				errors.add(messageSource.getMessage("error.CodeNoDeleted", null, locale));
				result.setErrorsList(errors);
			}

			t.setDeleted(false);
			t.setInfo(topic.getInfo());
			boolean r = daoTopic.saveTopic(t);
			if(r) 
				result.setSingleElement(t);	

		}
		return result;
	}

	public boolean uploadCSV(UploadForm upload, Long id_module) {
		CsvPreference prefers =
				new CsvPreference.Builder(upload.getQuoteChar().charAt(0), upload
						.getDelimiterChar().charAt(0), upload.getEndOfLineSymbols())
						.build();

				List<Topic> list = null;
				try {
					FileItem fileItem = upload.getFileData().getFileItem();
					TopicUpload topicUpload = new TopicUpload();
					
					Module m = serviceModule.getModule(id_module).getSingleElement();
					list = topicUpload.readCSVTopicToBean(fileItem.getInputStream(),
							upload.getCharset(), prefers, m);

					return daoTopic.persistListTopics(list);

				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
		
	}
}

