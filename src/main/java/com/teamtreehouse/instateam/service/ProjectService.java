package com.teamtreehouse.instateam.service;

import java.util.List;

import com.teamtreehouse.instateam.model.Project;

public interface ProjectService
{
    List<Project> findAll();
    Project findById(Long id);
    void save(Project project);
    void delete(Project project);
}
