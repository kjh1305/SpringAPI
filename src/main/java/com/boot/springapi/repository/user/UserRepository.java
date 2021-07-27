package com.boot.springapi.repository.user;



import com.boot.springapi.domain.user.User;
import com.boot.springapi.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void insert(User user);
    List<User> userList();
    List<User> userListPaging(Pagination pagination);
    User findUserId(long id);
    User findUserName(String name);
    Optional<User> findUserEmail(String email);
    void update(User user);
    void delete(long id);
    int userCnt();
}
