package com.tx0x.nocalleridbackend.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.tx0x.nocalleridbackend.cipher.BasicCipher;
import com.tx0x.nocalleridbackend.dto.User;
import com.tx0x.nocalleridbackend.service.KakaoService;
import com.tx0x.nocalleridbackend.service.OAuth2Service;
import com.tx0x.nocalleridbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class KakaoServiceImpl implements KakaoService {

    private final UserService userService;
    private final OAuth2Service oAuth2Service;

    public KakaoServiceImpl(UserService userService, OAuth2Service oAuth2Service) {
        this.userService = userService;
        this.oAuth2Service = oAuth2Service;
    }

    @Override
    public boolean createUser(User user) throws NoSuchAlgorithmException {
        // 가입된 회원 아닐 시, 가입
        int id = userService.createUser(user);
        String hashed = new BasicCipher().encode(Integer.toString(id));
        user.setId(id);
        user.setUrl(hashed);
        return userService.updateUser(user);
    }

    @Override
    public JsonNode getUserByKakaoToken(String code, String path) {
        JsonNode jsonNode = oAuth2Service.getToken(code, path);
        String token = jsonNode.get("access_token").toString();
        return oAuth2Service.getUser(token);
    }

    @Override
    public User newKakaoUser(JsonNode jsonNode) {
        JsonNode kakaoAccount = jsonNode.get("kakao_account");
        String email = kakaoAccount.get("email").asText();
        String birthday = kakaoAccount.get("birthday").asText();

        // 가입 체크
        User user = new User();
        user.setEmail(email);
        user.setBirth(birthday);

        return user;
    }

}
