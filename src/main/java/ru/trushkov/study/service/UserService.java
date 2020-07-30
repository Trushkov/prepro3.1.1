package ru.trushkov.study.service;

import ru.trushkov.study.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User getUser(long id);

    void remove(long id);

    void updateUser(User user);

    List<User> getUsers();
}
