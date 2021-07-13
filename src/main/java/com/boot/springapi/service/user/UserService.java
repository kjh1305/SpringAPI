package com.boot.springapi.service.user;

import com.boot.springapi.domain.user.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
    User findById(User id);
    User findByName(User name);
    void update(User user);
    void delete(User id);
}
