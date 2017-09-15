package com.teamtreehouse.instateam.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;
import com.teamtreehouse.instateam.model.Role;

@Repository
public class ProjectCollaboratorRolesImp implements ProjectCollaboratorRolesDao
{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ProjectCollaboratorRoles> findAll()
	{
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProjectCollaboratorRoles> criteria = builder.createQuery(ProjectCollaboratorRoles.class);
        criteria.from(ProjectCollaboratorRoles.class);
        List<ProjectCollaboratorRoles> pcr = session.createQuery(criteria).getResultList();
        session.close();
        return pcr;
	}

	@Override
	public ProjectCollaboratorRoles findByObjects(Role role, Collaborator collaborator, Project project)
	{
		/*
		Session session = sessionFactory.openSession();
		session.get
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProjectCollaboratorRoles> criteria = builder.createQuery(ProjectCollaboratorRoles.class);
        criteria.from(ProjectCollaboratorRoles.class);
        ProjectCollaboratorRoles pcr = session.createQuery(criteria).getSingleResult();
        session.close();
        */
        return null;
	}

	@Override
	public ProjectCollaboratorRoles findById(Long role, Long collaborator, Long project)
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(ProjectCollaboratorRoles pcr)
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(pcr);
        session.getTransaction().commit();

        session.close();
		
	}

	@Override
	public void delete(ProjectCollaboratorRoles pcr)
	{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(pcr);
        session.getTransaction().commit();

        session.close();
		
	}

	@Override
	public ProjectCollaboratorRoles findProjectsByObjects(Project project)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectCollaboratorRoles findProjectsById(Long project)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
