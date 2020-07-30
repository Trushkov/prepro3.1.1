package ru.trushkov.study.service;

import ru.trushkov.study.model.Role;

public interface RoleService {

    boolean hasRole(String name);

    void addRole(Role name);

    Role getRole(String name);
}
