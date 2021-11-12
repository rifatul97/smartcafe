package com.project.smartcafe.service;

import com.project.smartcafe.domain.user.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> find(long id);

    Optional<User> findByUsername(String username);

    Iterable<User> findAll();

}
