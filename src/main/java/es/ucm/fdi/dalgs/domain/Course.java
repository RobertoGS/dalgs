package es.ucm.fdi.dalgs.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;




@Entity
@Table(name = "course", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"id_subject", "id_academicterm" }) })
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_course")
	private Long id;


	@Column(name = "isDeleted", nullable = false, columnDefinition = "boolean default false")
	private Boolean isDeleted;

 
	@ManyToOne
	@JoinColumn(name = "id_subject")
	private Subject subject;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Collection<Activity> activities = new ArrayList<Activity>();

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Collection<Group> groups = new ArrayList<Group>();

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_academicterm")
	private AcademicTerm academicTerm;


	@ManyToOne
	@JoinColumn(name = "id_coordinator")
	private User coordinator; 
	
	public User getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(User coordinator) {
		this.coordinator = coordinator;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Course() {
		super();
		this.isDeleted=false;

	}

	public AcademicTerm getAcademicTerm() {
		return academicTerm;
	}

	public void setAcademicTerm(AcademicTerm academicTerm) {
		this.academicTerm = academicTerm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}

	public Collection<Group> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}

}