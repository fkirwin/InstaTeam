package com.teamtreehouse.instateam.service;

import java.util.List;

import com.teamtreehouse.instateam.model.Assignments;

public interface AssignmentsService
{
    List<Assignments> findAll();
    Assignments findById(Long role, Long collaborator, Long project);
    List<Assignments> findProjectsById(Long project);
    void save(Assignments pcr);
    void delete(Assignments pcr);
}
