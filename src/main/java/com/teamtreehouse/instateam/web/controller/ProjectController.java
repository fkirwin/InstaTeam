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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
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
	
	@RequestMapping("/index")
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
		
        return "project/index";
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
    public String saveAddedProject(@Valid Project project, @RequestParam(name="rolesNeeded") List<Role> rolesNeeded, BindingResult result, RedirectAttributes redirectAttributes) 
    {   
		project.setRolesNeeded(rolesNeeded);
		projectService.save(project);
        return "redirect:/index";
    }
	
	@RequestMapping("/editproject")
    public String editProject(Model model) 
    {   
        return "project/edit_project";
    }
	
	@RequestMapping(value="/editproject/{projectId}", method = RequestMethod.POST)
    public String saveEditedProject(@Valid Project project, Model model) 
    {   
		projectService.save(project);
        return "redirect:/index";
    }
	
	
	@RequestMapping("/projectdetail")
    public String getProjectDetails(Model model) 
    {   
        return "project/project_detail";
    }
	
	@RequestMapping("/projectcollaborators")
    public String getProjectCollaborators(Model model) 
    {   
        return "project/project_collaborators";
    }
	
	@RequestMapping("/addprojectcollaborators")
    public String addProjectCollaborators(Model model) 
    {   
        return "project/project_collaborators";
    }
	
	@RequestMapping("/removeprojectcollaborators")
    public String removeProjectCollaborators(Model model) 
    {   
        return "project/project_collaborators";
    }
    
}
