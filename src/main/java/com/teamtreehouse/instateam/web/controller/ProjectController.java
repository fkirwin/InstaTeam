package com.teamtreehouse.instateam.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.ProjectCollaboratorRoleService;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.RoleService;

@Controller
public class ProjectController
{
	@Autowired
	private RoleService roleService;
	@Autowired
	private CollaboratorService collaboratorService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectCollaboratorRoleService projectCollaboratorRoleService;
	
	@RequestMapping(value={"/index","/",""})
    public String getProjectIndex(Model model) 
    {   
		if(!model.containsAttribute("project")) 
		{
			Role role = new Role.RoleBuilder().withName("placeholder").build();
			List<Role> placeholderRole = new ArrayList<Role>();
			placeholderRole.add(role);
			model.addAttribute("project", new Project.ProjectBuilder().withName("New Project").withRoles(placeholderRole).build());
        }

		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);

		List<Collaborator> collaborators = collaboratorService.findAll();
		model.addAttribute("collaborators", collaborators);
		
		List<Project> projects = projectService.findAll();
		model.addAttribute("projects",projects);
		
        return "index";
    }
	
	@RequestMapping("/addproject")
	public String addProject(Model model)
	{
		if(!model.containsAttribute("project")) 
		{
			Role role = new Role.RoleBuilder().withName("placeholder").build();
			List<Role> placeholderRole = new ArrayList<Role>();
			placeholderRole.add(role);
			model.addAttribute("project", new Project.ProjectBuilder().withName("New Project").withRoles(placeholderRole).build());
        }
		
		//Always want current roles
		List<Role> roles = roleService.findAll();
		
		//get existing attribute for posting
		//Map<String, Object> modelAttributes = model.asMap();
		//Project targetProject = (Project) modelAttributes.get("project");
		
		//Add model attributes
		model.addAttribute("roles", roles);
		model.addAttribute("action","/saveaddedproject");

		
		return "project/edit_project";
	}
	
	@RequestMapping(value="/saveaddedproject", method = RequestMethod.POST)
    public String saveAddedProject(@Valid Project project, 
    								@RequestParam(value="roles") List<String> rolesNeeded, 
    								BindingResult result, 
    								RedirectAttributes redirectAttributes) 
    {   
		List<Role> roles = new ArrayList<Role>();
		
		for(String role: rolesNeeded)
		{
			roles.add(roleService.findById(Long.parseLong(role)));
		}
			
		project.setRolesNeeded(roles);
		
		projectService.save(project);
        return "redirect:/index";
    }
	
	@RequestMapping("/editproject/{projectId}")
    public String editProject(@PathVariable Long projectId, Model model) 
    {   
		Project project = projectService.findById(projectId);
		List<Role> currentRoles = project.getRolesNeeded();
		model.addAttribute("project", project);
		model.addAttribute("currentRoles", currentRoles);
		
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);

		
        return "project/edit_project";
    }
	
	@RequestMapping(value="/editproject/{projectId}", method = RequestMethod.POST)
    public String saveEditedProject(@Valid Project project, 
    								@RequestParam(value="roles") List<String> rolesNeeded, 
    								Model model) 
    {   
		List<Role> roles = new ArrayList<Role>();
		
		for(String role: rolesNeeded)
		{
			roles.add(roleService.findById(Long.parseLong(role)));
		}
			
		project.setRolesNeeded(roles);
		projectService.save(project);
        return "redirect:/index";
    }
	
	
	@RequestMapping("/projectdetail/{projectId}")
    public String getProjectDetails(@PathVariable Long projectId, Model model) 
    {   
		Project project = projectService.findById(projectId);
		//List<Role> currentRoles = project.getRolesNeeded();
		//List<Collaborator> currentCollaborators = project.getCollaborators();
		List<ProjectCollaboratorRoles> pcr = projectCollaboratorRoleService.findProjectsById(projectId);
		List<Role> currentRoles = new ArrayList<Role>();
		List<Collaborator> currentCollaborators = new ArrayList<Collaborator>();
		for(ProjectCollaboratorRoles item : pcr)
		{
			currentRoles.add(item.getRole());
			currentCollaborators.add(item.getCollaborator());
		}
		
		model.addAttribute("project", project);
		model.addAttribute("currentRoles", currentRoles);
		model.addAttribute("currentCollaborators", currentCollaborators);
		model.addAttribute("pcr",pcr);
		
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		
		List<Collaborator> collaborators = collaboratorService.findAll();
		model.addAttribute("collaborators", collaborators);
		
        return "project/project_detail";
    }
	
	@RequestMapping("/projectcollaborators/{projectId}")
    public String getProjectCollaborators(@PathVariable Long projectId, Model model) 
    {   
		Project project = projectService.findById(projectId);
		//List<Role> currentRoles = project.getRolesNeeded();
		//List<Collaborator> currentCollaborators = project.getCollaborators();
		List<ProjectCollaboratorRoles> pcr = projectCollaboratorRoleService.findProjectsById(projectId);
		List<Role> currentRoles = new ArrayList<Role>();
		List<Collaborator> currentCollaborators = new ArrayList<Collaborator>();
		for(ProjectCollaboratorRoles item : pcr)
		{
			currentRoles.add(item.getRole());
			currentCollaborators.add(item.getCollaborator());
		}
		
		model.addAttribute("project", project);
		model.addAttribute("currentRoles", currentRoles);
		model.addAttribute("currentCollaborators", currentCollaborators);
		model.addAttribute("pcr",pcr);
		
		//List<Role> roles = roleService.findAll();
		//model.addAttribute("roles", roles);
		
		List<Collaborator> collaborators = collaboratorService.findAll();
		model.addAttribute("collaborators", collaborators);
		
        return "project/project_collaborators";
    }
	
	@RequestMapping(value="/changeprojectcollaborators/{projectId}", method = RequestMethod.POST)
    public String addProjectCollaborators(@Valid Project project, 
			@RequestParam(value="collaborators") List<String> collaborators,
			BindingResult br,
			Model model) 
    {   
		System.out.println(br.getAllErrors());
		List<Collaborator> collaboratorList = new ArrayList<Collaborator>();
		
		for(String cs: collaborators)
		{
			collaboratorList.add(collaboratorService.findById(Long.parseLong(cs)));
		}
			
		project.setCollaborators(collaboratorList);
		projectService.save(project);
        return "redirect:/index";
    }
	

}
