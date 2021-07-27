package com.boot.springapi.service.user;


import com.boot.springapi.domain.user.User;
import com.boot.springapi.pagination.Pagination;
import com.boot.springapi.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<User> findAllPaging(Pagination pagination) {
        return userRepository.userListPaging(pagination);
    }

    @Override
    public User findById(long id) {
        return userRepository.findUserId(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findUserName(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserEmail(email);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }


    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public int pageCnt() {
        return userRepository.userCnt();
    }


}
