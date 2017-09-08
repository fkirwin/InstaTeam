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

import com.teamtreehouse.instateam.dao.RoleDao;
import com.teamtreehouse.instateam.dao.RoleDaoImp;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Role;

@Service
public class RoleServiceImp implements RoleService
{
	@Autowired
    private RoleDao roleDao;

	@Override
	public List<Role> findAll()
	{
		List<Role> roles = roleDao.findAll();
		
		return roles;
	}

	@Override
	public Role findById(Long id)
	{
		Role role = roleDao.findById(id);
		
		return role;
	}

	@Override
	public void save(Role role)
	{
		roleDao.save(role);
	}

	@Override
	public void delete(Role role)
	{
        roleDao.delete(role);
	}

	@Override
	public Role findByName(String name)
	{
		// TODO Auto-generated method stub
		return roleDao.findByName(name);
	}
}
