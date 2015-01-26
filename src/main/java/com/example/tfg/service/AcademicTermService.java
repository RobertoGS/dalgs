package com.example.tfg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tfg.domain.AcademicTerm;

@Service
public interface AcademicTermService {
	public boolean addAcademicTerm(AcademicTerm academicTerm);
	//public List<AcademicTerm> getAll();
	public boolean modifyAcademicTerm(AcademicTerm academicTerm, Long id_academic);
	public  List<AcademicTerm> getAcademicsTerm(Integer pageIndex);//String term);
	
	//public boolean deleteTerm(String term);
	public boolean deleteAcademicTerm(Long id);
	//public boolean deleteAcademicTerm(String term, Long id_degree);

	//public List<AcademicTerm> getAcademicTermsForDegree(Long id_degree);
	//public List<String> getAllTerms();
	public AcademicTerm getAcademicTerm( Long id_academic);
	//public AcademicTerm getAcademicTermDegree(String term, Long id_degree);

	
	public boolean modifyTerm(String term, String newTerm) ;
	public Integer numberOfPages();

}
