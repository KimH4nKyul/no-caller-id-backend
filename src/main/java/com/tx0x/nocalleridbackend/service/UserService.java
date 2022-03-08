package com.tx0x.nocalleridbackend.service;

import com.tx0x.nocalleridbackend.dto.User;

import java.util.List;

public interface UserService {
    // Token
    int updateToken(User user) throws Exception;
    int getTokenBalance(int id);

    // User
    User getUserByEmail(String email);
    boolean updateUser(User user);
    int createUser(User user);
    List<User> getUsers();
    String getUrlById(int id);
    boolean getUserByEmailAndBirth(User user) throws Exception;
    User getUserById(int id);
}
