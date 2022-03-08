package com.tx0x.nocalleridbackend.mapper;

import com.tx0x.nocalleridbackend.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    // Token
    boolean updateToken(User value);
    int getTokenBalance(int id);
    // User
    User getUserByEmail(String email);
    boolean updateUser(User user);
    boolean createUser(User user);
    List<User> getUsers();
    String getUrlById(int id);
    boolean getUserByEmailAndBirth(User user);
    User getUserById(int id);
}
