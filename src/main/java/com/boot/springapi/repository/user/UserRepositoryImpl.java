package com.boot.springapi.repository.user;


import com.boot.springapi.domain.user.User;
import com.boot.springapi.domain.user.UserList;
import com.boot.springapi.mapper.user.UserMapper;
import com.boot.springapi.pagination.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Slf4j
public class UserRepositoryImpl implements UserRepository{

    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> userList() {
        List<User> list = userMapper.select();
        return list;
    }

    @Override
    public List<UserList> userListPaging(Pagination pagination) {
        return userMapper.selectPaging(pagination);
    }

    @Override
    public User findUserId(long id) {
        return userMapper.selectUserId(id);
    }

    @Override
    public User findUserName(String name) {
        return userMapper.selectUserName(name);
    }

    @Override
    public Optional<User> findUserEmail(String email) {
        return userMapper.selectUserEmail(email);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(long id) {
        userMapper.delete(id);
    }

    @Override
    public int userCnt() {
        return userMapper.userCnt();
    }
}
