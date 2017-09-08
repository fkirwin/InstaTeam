package com.teamtreehouse.instateam.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.teamtreehouse.instateam.model.Collaborator.CollaboratorBuilder;

@Entity
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 2, max = 25)
    private String name;
    
    
    //Default constructor
    public Role(){};

	public Role(RoleBuilder builder)
	{
		this.name=builder.name;
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
    
	public static class RoleBuilder
	{
        private String name;

        public RoleBuilder() 
        {
            ;
        }

        public RoleBuilder withName(String name) 
        {
            this.name = name;
            return this;
        }
        
        public Role build() 
        {
            return new Role(this);
        }
	}
}
