package com.teamtreehouse.instateam.service;

import java.util.List;

import com.teamtreehouse.instateam.model.Role;

public interface RoleService
{
    List<Role> findAll();
    Role findById(Long id);
    Role findByName(String name);
    void save(Role role);
    void delete(Role role);
}
