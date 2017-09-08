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

import com.teamtreehouse.instateam.dao.ProjectDao;
import com.teamtreehouse.instateam.model.Project;

@Service
public class ProjectServiceImp implements ProjectService
{
	@Autowired
    private ProjectDao projectDao;

	@Override
	public List<Project> findAll()
	{
		List<Project> projects = projectDao.findAll();
		
		return projects;
	}

	@Override
	public Project findById(Long id)
	{
		Project project = projectDao.findById(id);
		
		return project;
	}

	@Override
	public void save(Project project)
	{
       projectDao.save(project);
	}

	@Override
	public void delete(Project project)
	{
       projectDao.delete(project);
	}
}
