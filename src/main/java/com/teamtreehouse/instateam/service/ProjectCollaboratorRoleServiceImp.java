package com.teamtreehouse.instateam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtreehouse.instateam.dao.CollaboratorDao;
import com.teamtreehouse.instateam.dao.ProjectCollaboratorRolesImp;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;
import com.teamtreehouse.instateam.model.Role;

@Service
public class ProjectCollaboratorRoleServiceImp implements ProjectCollaboratorRoleService
{
	@Autowired
    private ProjectCollaboratorRolesImp pcrDao;
	
	@Override
	public List<ProjectCollaboratorRoles> findAll()
	{
		return pcrDao.findAll();
	}

	@Override
	public ProjectCollaboratorRoles findById(Long role, Long collaborator, Long project)
	{
		// TODO Auto-generated method stub
		return pcrDao.findById(role, collaborator, project);
	}

	@Override
	public void save(ProjectCollaboratorRoles pcr)
	{
		pcrDao.save(pcr);
		
	}

	@Override
	public void delete(ProjectCollaboratorRoles pcr)
	{
		// TODO Auto-generated method stub
		pcrDao.delete(pcr);
		
	}

	@Override
	public List<ProjectCollaboratorRoles> findProjectsById(Long project)
	{
		// TODO Auto-generated method stub
		return pcrDao.findProjectsById(project);
	}
	
	@Override
	public void save(List<ProjectCollaboratorRoles> pcr, List<Role> pcrRolesToAdd, Project project)
	{
	
		for(ProjectCollaboratorRoles each: pcr)
		{
			pcrRolesToAdd.remove(each.getRole());
		}
			
		for(Role role : pcrRolesToAdd)
		{
			ProjectCollaboratorRoles pcr1 = new ProjectCollaboratorRoles(role,null,project);
			save(pcr1);
		}
		
	}
	
	@Override
	public void delete(List<ProjectCollaboratorRoles> pcr, List<Role> pcrRolesToRemove, List<Role> roles)
	{
		for(ProjectCollaboratorRoles each: pcr)
		{
			pcrRolesToRemove.add(each.getRole());
		}
		
		pcrRolesToRemove.removeAll(roles);
		
		for(Role role: pcrRolesToRemove)
		{
			ProjectCollaboratorRoles pcr2 = pcr.stream().filter(prole->prole.getRole().equals(role)).findFirst().orElse(null);
			if(pcr2==null)
			{
				;
			}
			else
			{
				delete(pcr2);
			}
		}
	}


}
