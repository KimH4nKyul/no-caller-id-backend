package com.tx0x.nocalleridbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tx0x.nocalleridbackend.dto.User;

import java.security.NoSuchAlgorithmException;

public interface KakaoService {
    User newKakaoUser(JsonNode jsonNode);
    JsonNode getUserByKakaoToken(String code, String path);
    boolean createUser(User user) throws NoSuchAlgorithmException;
}
