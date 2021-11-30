package com.nixsolution.service;

import com.nixsolution.entity.Role;
import com.nixsolution.entity.User;

import java.util.List;

public interface RoleService {
    Role findRoleByName(String name);

    void save(Role role);

    void update(Role role);

    void delete(long id);

    List<Role> findAll();

    Role findById(long id);

    Role findByName(String name);



}
