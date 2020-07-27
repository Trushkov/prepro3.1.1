package ru.leodev.examples.springboot.springbootwebspringsecurity.service;

import ru.leodev.examples.springboot.springbootwebspringsecurity.model.Role;

public interface RoleService {

    boolean hasRole(String name);

    void addRole(Role name);

    Role getRole(String name);
}
