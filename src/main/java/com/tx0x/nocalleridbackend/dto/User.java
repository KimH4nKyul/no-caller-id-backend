package com.tx0x.nocalleridbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private int id;
    private String email;
    private String birth;
    private int token;
    private String url;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getBirth() {
//        return birth;
//    }
//
//    public void setBirth(String birth) {
//        this.birth = birth;
//    }
//
//    public int getToken() {
//        return token;
//    }
//
//    public void setToken(int token) {
//        this.token = token;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", birth='" + birth + '\'' +
//                ", token=" + token +
//                ", url='" + url + '\'' +
//                '}';
//    }
}
