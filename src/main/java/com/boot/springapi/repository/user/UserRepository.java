package com.boot.springapi.repository.user;



import com.boot.springapi.domain.user.User;

import java.util.List;

public interface UserRepository {
    void insert(User user);
    List<User> userList();
}
