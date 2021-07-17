package com.boot.springapi.repository.user;


import com.boot.springapi.domain.user.User;
import com.boot.springapi.mapper.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        log.info("user: {}", user);
        userMapper.insert(user);
    }

    @Override
    public List<User> userList() {
        List<User> list = userMapper.select();
        return list;
    }
}
