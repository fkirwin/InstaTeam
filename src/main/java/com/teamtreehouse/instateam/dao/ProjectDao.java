package com.teamtreehouse.instateam.dao;

import java.util.List;

import com.teamtreehouse.instateam.model.Project;

public interface ProjectDao
{
    List<Project> findAll();
    Project findById(Long id);
    void save(Project project);
    void delete(Project project);
}
