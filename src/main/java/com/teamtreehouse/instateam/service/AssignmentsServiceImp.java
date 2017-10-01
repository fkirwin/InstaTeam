package com.teamtreehouse.instateam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtreehouse.instateam.dao.AssignmentsDao;
import com.teamtreehouse.instateam.dao.CollaboratorDao;
import com.teamtreehouse.instateam.dao.ProjectCollaboratorRolesImp;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Assignments;

@Service
public class AssignmentsServiceImp implements AssignmentsService
{
	@Autowired
    private AssignmentsDao pcrDao;
	
	@Override
	public List<Assignments> findAll()
	{
		return pcrDao.findAll();
	}

	@Override
	public Assignments findById(Long role, Long collaborator, Long project)
	{
		// TODO Auto-generated method stub
		return pcrDao.findById(role, collaborator, project);
	}

	@Override
	public void save(Assignments pcr)
	{
		pcrDao.save(pcr);
		
	}

	@Override
	public void delete(Assignments pcr)
	{
		// TODO Auto-generated method stub
		pcrDao.delete(pcr);
		
	}

	@Override
	public List<Assignments> findProjectsById(Long project)
	{
		// TODO Auto-generated method stub
		return pcrDao.findProjectsById(project);
	}


}
