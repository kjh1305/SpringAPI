package com.boot.springapi.service.user;

import com.boot.springapi.domain.user.User;
import com.boot.springapi.pagination.Pagination;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
    List<User> findAllPaging(Pagination pagination);
    User findById(long id);
    User findByName(String name);
    void update(User user);
    void delete(long id);
    int pageCnt();
}
