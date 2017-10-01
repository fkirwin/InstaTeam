package com.teamtreehouse.instateam.service;

import java.util.List;

import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;
import com.teamtreehouse.instateam.model.Role;

public interface ProjectCollaboratorRoleService
{
    List<ProjectCollaboratorRoles> findAll();
    ProjectCollaboratorRoles findById(Long role, Long collaborator, Long project);
    List<ProjectCollaboratorRoles> findProjectsById(Long project);
    void save(ProjectCollaboratorRoles pcr);
    void delete(ProjectCollaboratorRoles pcr);
	void save(List<ProjectCollaboratorRoles> pcr, List<Role> pcrRolesToAdd, Project project);
	void delete(List<ProjectCollaboratorRoles> pcr, List<Role> pcrRolesToRemove, List<Role> roles);
}
