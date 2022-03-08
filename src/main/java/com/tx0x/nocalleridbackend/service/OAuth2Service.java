package com.tx0x.nocalleridbackend.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface OAuth2Service {

    JsonNode getToken(String code, String path); // 인가코드 받기
    JsonNode getUser(String token); // 토큰 받기
}
