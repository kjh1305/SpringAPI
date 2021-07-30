package com.boot.springapi.service.user;

import com.boot.springapi.domain.user.User;
import com.boot.springapi.domain.user.UserList;
import com.boot.springapi.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);
    List<User> findAll();
    List<UserList> findAllPaging(Pagination pagination);
    User findById(long id);
    User findByName(String name);
    Optional<User> findByEmail(String email);
    void update(User user);
    void delete(long id);
    int pageCnt();
}
