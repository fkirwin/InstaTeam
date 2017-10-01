package com.teamtreehouse.instateam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Null;

import org.h2.util.DateTimeUtils;
import org.hibernate.annotations.Columns;

import com.teamtreehouse.instateam.service.RoleService;

@Entity
public class Assignments implements Serializable
{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

	//@Id
    @ManyToMany
    @JoinColumn(name="projectId", nullable=true)
    private List<Project> project;
	
	//@Id
	@ManyToMany
	@JoinColumn(name="roleId", nullable=true)
    private List<Role> roles;

	//@Id
	@ManyToMany
	@JoinColumn(name="collaboratorId", nullable=true)
    private List<Collaborator> collaborator;
    
	private Date dateCreated;
	
	
	public Long getId()
	{
		return id;
	}



	public void setId(Long id)
	{
		this.id = id;
	}

	
	
	public Date getDateCreated()
	{
		return dateCreated;
	}



	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}



	public Assignments()
	{
		// TODO Auto-generated constructor stub
	}

	

	public Assignments(List<Project> project,List<Role> roles, List<Collaborator> collaborator)
	{
		this.project = project;
		this.roles = roles;
		this.collaborator = collaborator;
	}



	public List<Project> getProject()
	{
		return project;
	}


	public void setProject(List<Project> project)
	{
		this.project = project;
	}


	public List<Role> getRoles()
	{
		return roles;
	}


	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}


	public List<Collaborator> getCollaborator()
	{
		return collaborator;
	}


	public void setCollaborator(List<Collaborator> collaborator)
	{
		this.collaborator = collaborator;
	}
	
	
	

}
