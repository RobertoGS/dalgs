package com.example.tfg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tfg.domain.Activity;
import com.example.tfg.domain.CompetenceStatus;



@Service
public interface ActivityService {
	public boolean addActivity(Activity activity, Long id_course);
	public List<Activity> getAll();
	public boolean modifyActivity(Activity activity, Long id_activity, Long id_course);
	public  Activity getActivity(Long id);
	public boolean deleteActivity(Long id);
	public List<Activity> getActivitiesForCourse(Long id_course);
	public String getNextCode();
	//public boolean deleteActivityFromCourse(Long id_course, Long id_activity);
	public Activity getActivityByName(String string);
	public boolean deleteCompetenceActivity(Long id_competenceStatus,
			Long id_Activity);
	public boolean existsCompetenceStatus(Long id_activity, Long id_competence);
	public boolean addCompetences(Long id, CompetenceStatus competencestatus);

}
