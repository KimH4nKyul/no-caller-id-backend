package com.tx0x.nocalleridbackend.service.impl;

import com.tx0x.nocalleridbackend.dto.Message;
import com.tx0x.nocalleridbackend.mapper.MessageMapper;
import com.tx0x.nocalleridbackend.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public int createMessage(Message message) throws Exception {
        if(message.getImg().isBlank() || message.getText().isBlank() || message.getTo().isBlank()) {
            throw new Exception("[createMessage] 값이 없습니다.");
        }

        int id = 0;
        if(messageMapper.createMessage(message)) {
            id = message.getId();
        }
        return id;
    }

    @Override
    public List<Message> getMessages() {
        return messageMapper.getMessages();
    }

    @Override
    public List<Message> getMessagesByUserId(int id) {
        return messageMapper.getMessagesByUserId(id);
    }

    @Override
    public Message getMessage(int id) {
        return messageMapper.getMessage(id);
    }
}
