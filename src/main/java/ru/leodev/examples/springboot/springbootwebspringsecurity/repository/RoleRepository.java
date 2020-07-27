package ru.leodev.examples.springboot.springbootwebspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leodev.examples.springboot.springbootwebspringsecurity.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
