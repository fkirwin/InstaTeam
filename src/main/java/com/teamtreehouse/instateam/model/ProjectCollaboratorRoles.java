package com.teamtreehouse.instateam.model;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.hibernate.annotations.Columns;

import com.teamtreehouse.instateam.service.RoleService;

@Entity
public class ProjectCollaboratorRoles implements Serializable
{
	/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Long id;
    */
    
	@Id
    @ManyToOne(optional=true)
    @JoinColumn(name="projectId", nullable=true)
    private Project project;
	
	@Id
	@ManyToOne(optional=true)
	@JoinColumn(name="roleId", nullable=true)
    private Role role;

	//@Id
	@ManyToOne(optional=true)
	@JoinColumn(name="collaboratorId", nullable=true, updatable=true)
    private Collaborator collaborator;
    
	
	public ProjectCollaboratorRoles()
	{
		// TODO Auto-generated constructor stub
	}
	
	public ProjectCollaboratorRoles(Role role, Collaborator collaborator,Project project)
	{
		this.role=role;
		this.collaborator=collaborator;
		this.project=project;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public Collaborator getCollaborator()
	{
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator)
	{
		this.collaborator = collaborator;
	}

	public Project getProject()
	{
		return project;
	}
	

	public void setProject(Project project)
	{
		this.project = project;
	}
	
	

}
