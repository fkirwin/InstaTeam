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

@Entity
public class ProjectCollaboratorRoles implements Serializable
{
	@Id
	@ManyToOne
	
	@JoinTable(name="Role", joinColumns=@JoinColumn(name="role_id", referencedColumnName="Id"))
	//@Column(name="role_id")
    private Role role;

	@Id
	@ManyToOne
	@JoinColumn(name="Id")
	@JoinTable(name="Collaborator")
	//@Column(name="collaborator_id")
    private Collaborator collaborator;
    
	@Id
    @ManyToOne
	@JoinColumn(name="Id")
	@JoinTable(name="Project")
	//@Column(name="project_id")
    private Project project;
	
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
