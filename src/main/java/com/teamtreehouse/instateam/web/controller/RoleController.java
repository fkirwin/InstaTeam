package com.teamtreehouse.instateam.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.RoleService;

@Controller
public class RoleController
{
	@Autowired
	private RoleService roleService;
	
	
	//Landing page for roles.  Create a new role object to be used if the user wants to save it.
	@RequestMapping("/roles")
    public String listRoles(Model model) 
    {
		if(!model.containsAttribute("role")) 
		{
            model.addAttribute("role", new Role());
        }
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		model.addAttribute("action","/roles/addrolepost");
        model.addAttribute("heading","AddRole");
        model.addAttribute("submit","Add");
		
        return "role/roles";
    }
	
    // Add the role and stay on the page
    @RequestMapping(value = "/roles/addrolepost", method = RequestMethod.POST)
    public String addCategory(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) 
    {
        // TODO: Add category if valid data was received
        if(result.hasErrors()) 
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);
            redirectAttributes.addFlashAttribute("role",role);

            return "redirect:/roles";
        }
        roleService.save(role);

        return "redirect:/roles";
    }

	//Redirect to a new place to get the new name
	@RequestMapping("roles/{roleId}/editrole")
    public String formEditRole(@PathVariable Long roleId, Model model) 
	{
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("role")) 
        {
        	Role role = roleService.findById(roleId);
            model.addAttribute("role", role);
        }
        model.addAttribute("action",String.format("/roles/%s",roleId));
        model.addAttribute("heading","Edit Role");
        model.addAttribute("submit","Update");

        return "role/editrole";
    }
	
	//Apply new name
    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.POST)
    public String updateRole(@PathVariable Long roleId, @RequestParam(name="name") String newName, Model model) 
    {
    	//Trying something old school and just getting the request param from form with name value.
    	Role role = roleService.findById(roleId);
    	role.setName(newName);
        roleService.save(role);

        return "redirect:/roles";
    }

}  

	
