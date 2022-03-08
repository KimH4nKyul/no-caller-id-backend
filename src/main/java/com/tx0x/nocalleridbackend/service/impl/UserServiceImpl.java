package com.tx0x.nocalleridbackend.service.impl;

import com.tx0x.nocalleridbackend.dto.User;
import com.tx0x.nocalleridbackend.mapper.UserMapper;
import com.tx0x.nocalleridbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public String getUrlById(int id) {
        return userMapper.getUrlById(id);
    }

    @Override
    public boolean getUserByEmailAndBirth(User user) throws Exception {
        if (user == null) throw new Exception("[getUserByEmailAndBirth] No user");
        return userMapper.getUserByEmailAndBirth(user);
    }

    @Override
    public int updateToken(User user) throws Exception{
        if ((user.getId() <= 0) || (user.getToken() <= 0)) {
            throw new Exception("[updateToken] 값이 0보다 작거나 같습니다.");
        }

        int id = 0;
        if(userMapper.updateToken(user)) {
            id = user.getId();
        }
        return id;
    }

    @Override
    public int getTokenBalance(int id) {
        return userMapper.getTokenBalance(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int createUser(User user) {
        int id = 0;
        if(userMapper.createUser(user)) {
            id = user.getId();
        }
        return id;
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
