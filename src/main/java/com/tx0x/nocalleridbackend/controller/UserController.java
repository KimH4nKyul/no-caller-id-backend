package com.tx0x.nocalleridbackend.controller;

import com.tx0x.nocalleridbackend.dto.User;
import com.tx0x.nocalleridbackend.jwt.JwtToken;
import com.tx0x.nocalleridbackend.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class UserController {

    private final UserService userService;
    private final JwtToken jwtToken;

    public UserController(UserService userService, JwtToken jwtToken) {
        this.userService = userService;
        this.jwtToken = jwtToken;
    }

    @GetMapping("/user")
    public User getUserByToken(String token) throws UnsupportedEncodingException {
        Claims claims = jwtToken.verificationJwtToken(token);
        String email = claims.get("id").toString() + "@" + claims.get("email").toString();
        return userService.getUserByEmail(email);
    }

    @GetMapping("/user/{id}/url")
    public String getUrlById(@PathVariable("id") int id) {
        return userService.getUrlById(id);
    }

    @PatchMapping("/user/{id}/token/{balance}")
    public int updateToken(@PathVariable("id") int id, @PathVariable("balance") int balance) throws Exception {
        User user = new User();
        user.setId(id);
        user.setToken(balance);
        return userService.updateToken(user);
    }

    @GetMapping("/user/{id}/token")
    public int getTokenBalance(@PathVariable("id") int id) {
        return userService.getTokenBalance(id);
    }

    @PostMapping("/user")
    public int createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }
}
