package com.teamtreehouse.instateam.dao;

import java.util.List;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;
import com.teamtreehouse.instateam.model.Role;

public interface ProjectCollaboratorRolesDao
{
    List<ProjectCollaboratorRoles> findAll();
    ProjectCollaboratorRoles findByObjects(Role role, Collaborator collaborator, Project project);
    ProjectCollaboratorRoles findById(Long role, Long collaborator, Long project);
    ProjectCollaboratorRoles findProjectsByObjects(Project project);
    ProjectCollaboratorRoles findProjectsById(Long project);
    void save(ProjectCollaboratorRoles pcr);
    void delete(ProjectCollaboratorRoles pcr);
}
