package com.springboot.service;

import com.springboot.model.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long id);

    public List<User> findAllUsers();

    public User saveUser(User user);

    public void deleteUserById(Long id);
}
