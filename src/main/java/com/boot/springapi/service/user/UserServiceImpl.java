package com.boot.springapi.service.user;


import com.boot.springapi.domain.user.User;
import com.boot.springapi.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void save(User user) {
        userRepository.insert(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.userList();
        return users;
    }

    @Override
    public User findById(User id) {
        return null;
    }

    @Override
    public User findByName(User name) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User id) {

    }


}
