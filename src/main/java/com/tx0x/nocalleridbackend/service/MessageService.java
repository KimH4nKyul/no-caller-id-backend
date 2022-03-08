package com.tx0x.nocalleridbackend.service;

import com.tx0x.nocalleridbackend.dto.Message;

import java.util.List;

public interface MessageService {
    int createMessage(Message message) throws Exception;
    Message getMessage(int id);
    List<Message> getMessages();
    List<Message> getMessagesByUserId(int id);
}
