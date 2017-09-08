package com.teamtreehouse.instateam.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Role;

@Repository
public class RoleDaoImp implements RoleDao
{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<Role> findAll()
	{
		Session session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		criteria.from(Role.class);
		List<Role> roles = session.createQuery(criteria).getResultList();
		session.close();
		
		return roles;
	}

	@Override
	public Role findById(Long id)
	{
		Session session = sessionFactory.openSession();
		Role role = session.get(Role.class, id);
		session.close();
		
		return role;
	}

	@Override
	public void save(Role role)
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(role);
        session.getTransaction().commit();

        session.close();
		
	}

	@Override
	public void delete(Role role)
	{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(role);
        session.getTransaction().commit();

        session.close();
		
	}

	@Override
	public Role findByName(String name)
	{
		Session session = sessionFactory.openSession();
		Role role = session.get(Role.class, name);
		session.close();
		
		return role;
	}
}
