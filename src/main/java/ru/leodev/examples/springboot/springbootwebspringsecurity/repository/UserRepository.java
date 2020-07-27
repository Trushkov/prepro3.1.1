package ru.leodev.examples.springboot.springbootwebspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leodev.examples.springboot.springbootwebspringsecurity.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);
}
