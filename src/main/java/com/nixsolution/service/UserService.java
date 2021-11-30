package com.nixsolution.service;

import com.nixsolution.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void update(User user);

    void delete(long id);

    List<User> findAll();

    User findById(long id);

    User findByLogin(String login);

    User findByEmail(String email);

}
