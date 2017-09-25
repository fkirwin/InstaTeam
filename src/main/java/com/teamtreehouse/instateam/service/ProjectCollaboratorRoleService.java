package com.teamtreehouse.instateam.service;

import java.util.List;

import com.teamtreehouse.instateam.model.ProjectCollaboratorRoles;

public interface ProjectCollaboratorRoleService
{
    List<ProjectCollaboratorRoles> findAll();
    ProjectCollaboratorRoles findById(Long role, Long collaborator, Long project);
    List<ProjectCollaboratorRoles> findProjectsById(Long project);
    void save(ProjectCollaboratorRoles pcr);
    void delete(ProjectCollaboratorRoles pcr);
}
