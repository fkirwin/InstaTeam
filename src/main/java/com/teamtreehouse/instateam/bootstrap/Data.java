package com.teamtreehouse.instateam.bootstrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.teamtreehouse.instateam.model.Assignments;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.AssignmentsService;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.CollaboratorServiceImp;
import com.teamtreehouse.instateam.service.ProjectCollaboratorRoleService;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.ProjectServiceImp;
import com.teamtreehouse.instateam.service.RoleService;
import com.teamtreehouse.instateam.service.RoleServiceImp;

@Component
public class Data implements ApplicationRunner
{
	@Autowired
	private CollaboratorService cs;
	@Autowired
	private ProjectService ps;
	@Autowired
	private RoleService rs;
	@Autowired
	private ProjectCollaboratorRoleService pcrs;
	@Autowired
	private AssignmentsService as;
	
	
	public Collaborator populateCollaborator(String name, Role role)
	{
		Collaborator collaborator = new Collaborator.CollaboratorBuilder().withName(name).withRole(role).build();
		cs.save(collaborator);
		
		return collaborator;
	}
	
	public Role populateRole(String name)
	{
		Role role = new Role.RoleBuilder().withName(name).build();
		rs.save(role);
		
		return role;
	}
	
	public Project populateProject(String name, 
			String status, 
			String description, 
			List<Role>roles, 
			List<Collaborator> collaborators)
	{
		Project project = new Project.ProjectBuilder()
				.withName(name)
				.withStatus(status)
				.withDescription(description)
				.withRoles(roles)
				.withCollaborators(collaborators)
				.build();
		ps.save(project);
		
		return project;
	}
	
	public void assignProject(Role role, Collaborator collaborator,Project project)
	{
		ProjectCollaboratorRoles pcr = new ProjectCollaboratorRoles(role, collaborator,project);
		pcrs.save(pcr);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		
			List<Role> roles= new ArrayList<Role>();
			roles.add(populateRole("Developer"));
			roles.add(populateRole("QA"));
			roles.add(populateRole("Scrum Master"));
			
			List<Collaborator> collaborators = new ArrayList<Collaborator>();
			collaborators.add(populateCollaborator("Tom", roles.get(0)));
			collaborators.add(populateCollaborator("Dick", roles.get(1)));	
			collaborators.add(populateCollaborator("Harry", roles.get(2)));
			
			/*
			List<Role> roles1 = new ArrayList<Role>();
			roles1.add(populateRole("Developer"));
			roles1.add(populateRole("QA"));
			
			
			List<Collaborator> collaborators1 = new ArrayList<Collaborator>();
			collaborators.add(populateCollaborator("Tom", roles.get(0)));
			collaborators.add(populateCollaborator("Dick", roles.get(1)));
			*/
	
			Project p1 = populateProject("Website", "active", "Making a website", roles, collaborators);
			Project p2 = populateProject("MobileApp", "active", "Making a mobile app", roles, collaborators);
			
			assignProject(roles.get(0), null, p1);
			assignProject(roles.get(1), collaborators.get(1), p1);
			assignProject(roles.get(2), collaborators.get(2), p2);
			assignProject(roles.get(0), collaborators.get(0), p2);
			
			List<Project> l = new ArrayList<Project>();
			l.add(p1);
			as.save(new Assignments(l,roles,collaborators));
		
	}
	
}
