package com.tx0x.nocalleridbackend.mapper;

import com.tx0x.nocalleridbackend.dto.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    // BASIC CREATE
    boolean createMessage(Message message);
    // BASIC READ
    Message getMessage(int id);
    List<Message> getMessages();
    List<Message> getMessagesByUserId(int id);
}
