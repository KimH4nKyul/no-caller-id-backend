package com.tx0x.nocalleridbackend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tx0x.nocalleridbackend.dto.User;
import com.tx0x.nocalleridbackend.jwt.JwtToken;
import com.tx0x.nocalleridbackend.service.KakaoService;
import com.tx0x.nocalleridbackend.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoController {
    private final JwtToken jwtToken;
    private final KakaoService kakaoService;
    private final UserService userService;

    public KakaoController(JwtToken jwtToken, KakaoService kakaoService, UserService userService) {
        this.jwtToken = jwtToken;
        this.kakaoService = kakaoService;
        this.userService = userService;
    }

    @GetMapping("/oauth2/{redirect-path}/kakao")
    public ResponseEntity loginWithKakao(@PathVariable("redirect-path") String path, @RequestParam("code") String code) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        JsonNode jsonNode = kakaoService.getUserByKakaoToken(code, path);
        User user = kakaoService.newKakaoUser(jsonNode);

        boolean checked = userService.getUserByEmailAndBirth(user);
        if(!checked) {
            kakaoService.createUser(user);
        }

        String jwt = jwtToken.createToken(user.getEmail(), (60*1000*60));
        headers.add("Location", "http://localhost:3000/" + path + "?token=" + jwt);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
