package com.teamtreehouse.instateam.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.AssignmentsService;
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
	@Autowired
	private AssignmentsService assignmentsService;
	
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
//TODO Make sure a project is allocated to the pcr table.
		
		for(String role: rolesNeeded)
		{
			roles.add(roleService.findById(Long.parseLong(role)));
		}
			
		project.setRolesNeeded(roles);
		projectService.save(project);
		
		ProjectCollaboratorRoles pcr = new ProjectCollaboratorRoles();
		pcr.setProject(project);
		
		for(String role: rolesNeeded)
		{
			Role r = roleService.findById(Long.parseLong(role));
			pcr.setRole(r);
			projectCollaboratorRoleService.save(pcr);
		}

        return "redirect:/index";
    }
	
	@RequestMapping("/editproject/{projectId}")
    public String editProject(@PathVariable Long projectId, Model model) 
    {   
		//TODO make sure pcr table is displayed instead of traditional table
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
    								@RequestParam(value="roles", required=false, defaultValue="null") List<String> rolesNeeded, 
    								BindingResult result,
    								RedirectAttributes redirectAttributes) 
    {   
		
		 if(result.hasErrors()) 
		 {
	            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);
	            redirectAttributes.addFlashAttribute("project", project);
	            return String.format("redirect:/index");
	       }
		
		if(rolesNeeded.equals("null")||rolesNeeded.get(0).equals("null"))
		{
			redirectAttributes.addFlashAttribute("flash",new FlashMessage("Please choose a role!", FlashMessage.Status.FAILURE));
			return "redirect:/editproject/{projectId}";
		}
		
		List<Role> roles = new ArrayList<Role>();
		List<Role> pcrRolesToAdd = new ArrayList<Role>();
		List<Role> pcrRolesToRemove = new ArrayList<Role>();
		List<ProjectCollaboratorRoles> pcr = projectCollaboratorRoleService.findProjectsById(project.getId());
		
			for(String role: rolesNeeded)
			{
					roles.add(roleService.findById(Long.parseLong(role)));
			}
				
			pcrRolesToAdd.addAll(roles);
				
			project.setRolesNeeded(roles);
			projectService.save(project);
				//these need refactoring probably...too tired.
			projectCollaboratorRoleService.save(pcr, pcrRolesToAdd, project);			
			projectCollaboratorRoleService.delete(pcr, pcrRolesToRemove, roles);
			redirectAttributes.addFlashAttribute("flash",new FlashMessage("Successful update!", FlashMessage.Status.SUCCESS));
		
        return "redirect:/editproject/{projectId}";
    }
	
	
	@RequestMapping("/projectdetail/{projectId}")
    public String getProjectDetails(@PathVariable Long projectId, Model model) 
    {   
		Project project = projectService.findById(projectId);
		List<Role> currentRoles = project.getRolesNeeded();
		//List<Collaborator> currentCollaborators = project.getCollaborators();
		List<ProjectCollaboratorRoles> pcr = projectCollaboratorRoleService.findProjectsById(projectId);
		//List<Role> currentRoles = new ArrayList<Role>();
		List<Collaborator> currentCollaborators = new ArrayList<Collaborator>();
		for(ProjectCollaboratorRoles item : pcr)
		{
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
		List<ProjectCollaboratorRoles> pcr = projectCollaboratorRoleService.findProjectsById(projectId);
		List<Role> currentRoles = new ArrayList<Role>();
		List<Collaborator> currentCollaborators = new ArrayList<Collaborator>();
		List<Collaborator> availableCollaborators = collaboratorService.findAll();
		for(ProjectCollaboratorRoles item : pcr)
		{
			currentRoles.add(item.getRole());
			currentCollaborators.add(item.getCollaborator());
		}

		model.addAttribute("project", project);
		model.addAttribute("currentRoles", currentRoles);
		model.addAttribute("currentCollaborators", currentCollaborators);
		model.addAttribute("pcr",pcr);
		model.addAttribute("collaborators",availableCollaborators);
		
		
        return "project/project_collaborators";
    }
	
	@RequestMapping(value="/projectcollaborators/{projectId}", method = RequestMethod.POST)
    public String addProjectCollaborators(
    		@RequestParam(value="project") String projectId,
			@RequestParam(value="collaborators") List<String> collaborators,
			@RequestParam(value="roles") List<String> rolesNeeded, 
			Model model) 
    {   
		
		ProjectCollaboratorRoles pcr = new ProjectCollaboratorRoles();
		pcr.setProject(projectService.findById(Long.parseLong(projectId)));
		
		for(int i = 0; i < rolesNeeded.size(); i ++)
		{
			pcr.setRole(roleService.findById(Long.parseLong(rolesNeeded.get(i))));
			if(collaborators.get(i).equals(null)||collaborators.get(i)==null||collaborators.get(i).equals(""))
			{
				projectCollaboratorRoleService.save(pcr);
			}
			else
			{
				pcr.setCollaborator(collaboratorService.findById(Long.parseLong(collaborators.get(i))));
				projectCollaboratorRoleService.save(pcr);
			}
		}

		return "redirect:/";
    }
	

}
