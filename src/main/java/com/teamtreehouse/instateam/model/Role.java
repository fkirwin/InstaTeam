package com.teamtreehouse.instateam.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    
    @OneToMany(mappedBy="role")
    private List<ProjectCollaboratorRoles> pcr;
    
    public List<ProjectCollaboratorRoles> getPcr()
	{
		return pcr;
	}

	public void setPcr(List<ProjectCollaboratorRoles> pcr)
	{
		this.pcr = pcr;
	}
    
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
