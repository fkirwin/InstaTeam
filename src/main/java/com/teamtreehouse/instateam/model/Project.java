package com.teamtreehouse.instateam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.teamtreehouse.instateam.model.Role.RoleBuilder;

@Entity
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    
    @NotNull
    private String description;
    
    @NotNull
    private String status;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> rolesNeeded = new ArrayList<Role>();
    
    @ManyToMany()
    private List<Collaborator> collaborators = new ArrayList<>();

    public Project(){};
    
	public Project(ProjectBuilder projectBuilder)
	{
		this.name=projectBuilder.name;
		this.status=projectBuilder.status;
		this.description=projectBuilder.description;
		this.rolesNeeded=projectBuilder.roles;
		this.collaborators=projectBuilder.collaborators;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<Role> getRolesNeeded()
	{
		return rolesNeeded;
	}

	public void setRolesNeeded(List<Role> rolesNeeded)
	{
		this.rolesNeeded = rolesNeeded;
	}

	public List<Collaborator> getCollaborators()
	{
		return collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators)
	{
		this.collaborators = collaborators;
	}
	
	public static class ProjectBuilder
	{
        private String name;
        private String status;
        private String description;
        private List<Role> roles;
        private List<Collaborator> collaborators;

        public ProjectBuilder() 
        {
            ;
        }

        public ProjectBuilder withName(String name) 
        {
            this.name = name;
            return this;
        }
        
        public ProjectBuilder withStatus(String status)
        {
        	this.status=status;
        	return this;
        }
        
        public ProjectBuilder withDescription(String description)
        {
        	this.description=description;
        	return this;
        }
        
        public ProjectBuilder withRoles(List<Role> roles)
        {
        	this.roles = roles;
        	return this;
        }
        
        public ProjectBuilder withCollaborators(List<Collaborator> collaborators)
        {
        	this.collaborators=collaborators;
        	return this;
        }
        
        public Project build() 
        {
            return new Project(this);
        }
	}
    
    
}
