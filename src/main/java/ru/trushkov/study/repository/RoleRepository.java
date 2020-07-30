package ru.trushkov.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trushkov.study.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
