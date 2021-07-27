package com.boot.springapi.repository.user;



import com.boot.springapi.domain.user.User;
import com.boot.springapi.pagination.Pagination;

import java.util.List;

public interface UserRepository {
    void insert(User user);
    List<User> userList();
    List<User> userListPaging(Pagination pagination);
    User findUserId(long id);
    User findUserName(String name);
    void update(User user);
    void delete(long id);
    int userCnt();
}
