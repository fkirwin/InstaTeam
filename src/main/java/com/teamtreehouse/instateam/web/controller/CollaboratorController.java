package com.teamtreehouse.instateam.web.controller;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.RoleService;

@Controller
public class CollaboratorController
{
	@Autowired
	private CollaboratorService collaboratorService;
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("/collaborators")
    public String listRoles(Model model) 
    {
		
		if(!model.containsAttribute("collaborator")) 
		{
            model.addAttribute("collaborator", new Collaborator());
        }
		//Always want current roles
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		//Always want current collaborators
		List<Collaborator> collaborators = collaboratorService.findAll();
		model.addAttribute("collaborators", collaborators);
		
		//model.addAttribute("action","/collaborators/update");
        model.addAttribute("heading","UpdateCollaborators");
        model.addAttribute("submit","Save");
        
        return "collaborator/collaborators";
    }

    @RequestMapping(value = "/collaborators/add", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator,  
    								@RequestParam(name="collaboratorrole") String roleId,
    								BindingResult result,  
    								Model model,
    								RedirectAttributes redirectAttributes) 
    {
    	 if(result.hasErrors()) {
             // Include validation errors upon redirect
             redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);

             // Add  category if invalid was received
             redirectAttributes.addFlashAttribute("category",collaborator);

             // Redirect back to the form
             return "redirect:/collaborators";
         }
    	Role role = roleService.findById(Long.parseUnsignedLong(roleId));
    	collaborator.setRole(role);
		collaboratorService.save(collaborator);
    	return "redirect:/collaborators";
    }
    
    @RequestMapping(value ="/collaborators/{collaboratorId}/edit")
    public String editCollaborator(@PathVariable Long collaboratorId,  Model model) 
    {
    	if(!model.containsAttribute("roles"))
    	{
    	List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
    	}
    	
        model.addAttribute("collaborator",collaboratorService.findById(collaboratorId));
    	
    	return "collaborator/editcollaborator";
    }
    
    @RequestMapping(value = "/collaborators/{collaboratorId}/update", method = RequestMethod.POST)
    public String updateCollaborator(@Valid Collaborator collaborator, 
    								@RequestParam(name="collaboratorrole") String roleId, 
    								BindingResult result,  
    								Model model, 
    								RedirectAttributes redirectAttributes) 
    {
        
       	 if(result.hasErrors()) 
       	 {
                // Include validation errors upon redirect
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);

                // Add  category if invalid was received
                redirectAttributes.addFlashAttribute("category",collaborator);

                // Redirect back to the form
                return "redirect:/collaborators/{collaboratorId}/edit";
          }

    	Role role = roleService.findById(Long.parseUnsignedLong(roleId));
    	collaborator.setRole(role);
    	collaboratorService.save(collaborator);
    	
    	
    	return "redirect:/collaborators";
    }
    
}
