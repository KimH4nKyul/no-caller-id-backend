package com.tx0x.nocalleridbackend.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
    private int id;
    private int userId;
    private String from;
    private String to;
    private String img;
    private String text;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public String getFrom() {
//        return from;
//    }
//
//    public void setFrom(String from) {
//        this.from = from;
//    }
//
//    public String getTo() {
//        return to;
//    }
//
//    public void setTo(String to) {
//        this.to = to;
//    }
//
//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    @Override
//    public String toString() {
//        return "Message{" +
//                "id=" + id +
//                ", userId=" + userId +
//                ", from='" + from + '\'' +
//                ", to='" + to + '\'' +
//                ", img='" + img + '\'' +
//                ", text='" + text + '\'' +
//                '}';
//    }
}
