package com.teamtreehouse.instateam.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtreehouse.instateam.model.Assignments;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Assignments;
import com.teamtreehouse.instateam.model.Role;

@Repository
public class AssignmentsDaoImp implements AssignmentsDao
{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Assignments> findAll()
	{
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Assignments> criteria = builder.createQuery(Assignments.class);
        criteria.from(Assignments.class);
        List<Assignments> pcr = session.createQuery(criteria).getResultList();
        session.close();
        return pcr;
	}

	@Override
	public Assignments findByObjects(Role role, Collaborator collaborator, Project project)
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
	public Assignments findById(Long role, Long collaborator, Long project)
	{
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Assignments> criteria = builder.createQuery(Assignments.class);											
        Root<Assignments> pcrRoot = criteria.from(Assignments.class);
        criteria.where(builder.equal(pcrRoot.get("role_id"), role))
        		.where(builder.equal(pcrRoot.get("collaborator_id"), collaborator))
				.where(builder.equal(pcrRoot.get("project_id"), project));
        Assignments pcr = session.createQuery(criteria).getSingleResult();
        
		return pcr;
	}


	@Override
	public void save(Assignments pcr)
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(pcr);
        session.getTransaction().commit();

        session.close();
		
	}

	@Override
	public void delete(Assignments pcr)
	{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(pcr);
        session.getTransaction().commit();

        session.close();
		
	}

	@Override
	public Assignments findProjectsByObjects(Project project)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assignments> findProjectsById(Long project)
	{
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Assignments> criteria = builder.createQuery(Assignments.class);											
        Root<Assignments> pcrRoot = criteria.from(Assignments.class);
        criteria.where(builder.equal(pcrRoot.get("project"), project));
        List<Assignments> pcr = session.createQuery(criteria).getResultList();
		return pcr;
	}

}
