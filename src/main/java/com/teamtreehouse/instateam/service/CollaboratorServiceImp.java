package com.teamtreehouse.instateam.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamtreehouse.instateam.dao.CollaboratorDao;
import com.teamtreehouse.instateam.model.Collaborator;

@Service
public class CollaboratorServiceImp implements CollaboratorService
{
	@Autowired
    private CollaboratorDao collaboratorDao;

	@Override
	public List<Collaborator> findAll()
	{
		List<Collaborator>  collaborators = collaboratorDao.findAll();
		
        return collaborators;
	}

	@Override
	public Collaborator findById(Long id)
	{
        Collaborator collaborator = collaboratorDao.findById(id);
        
        return collaborator;
	}

	@Override
	public void save(Collaborator collaborator)
	{
       collaboratorDao.save(collaborator);
	}

	@Override
	public void delete(Collaborator collaborator)
	{
		collaboratorDao.delete(collaborator);
	}
	
}
