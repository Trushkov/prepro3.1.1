package ru.trushkov.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trushkov.study.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);
}
