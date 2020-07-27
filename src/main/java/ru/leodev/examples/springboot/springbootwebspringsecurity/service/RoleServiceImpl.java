package ru.leodev.examples.springboot.springbootwebspringsecurity.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.leodev.examples.springboot.springbootwebspringsecurity.model.Role;
import ru.leodev.examples.springboot.springbootwebspringsecurity.repository.RoleRepository;


import static java.util.Objects.isNull;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImpl() {
    }

    @Override
    public boolean hasRole(String name){
        return !isNull(roleRepository.findByName(name));
    }

    @Override
    public void addRole(Role name) {
        roleRepository.save(name);
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
