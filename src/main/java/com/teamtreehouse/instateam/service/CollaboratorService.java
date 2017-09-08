package com.teamtreehouse.instateam.service;

import java.util.List;

import com.teamtreehouse.instateam.model.Collaborator;

public interface CollaboratorService
{
    List<Collaborator> findAll();
    Collaborator findById(Long id);
    void save(Collaborator collaborator);
    void delete(Collaborator collaborator);
}
