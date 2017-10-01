package com.teamtreehouse.instateam.dao;

import java.util.List;

import com.teamtreehouse.instateam.model.Assignments;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Assignments;
import com.teamtreehouse.instateam.model.Role;

public interface AssignmentsDao
{
    List<Assignments> findAll();
    Assignments findByObjects(Role role, Collaborator collaborator, Project project);
    Assignments findById(Long role, Long collaborator, Long project);
    Assignments findProjectsByObjects(Project project);
    List<Assignments> findProjectsById(Long project);
    void save(Assignments pcr);
    void delete(Assignments pcr);
}
