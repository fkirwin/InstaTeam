package com.teamtreehouse.instateam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtreehouse.instateam.dao.CollaboratorDao;
import com.teamtreehouse.instateam.dao.ProjectCollaboratorRolesImp;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;

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


}
