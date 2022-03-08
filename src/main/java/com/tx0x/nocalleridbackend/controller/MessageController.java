package com.tx0x.nocalleridbackend.controller;

import com.tx0x.nocalleridbackend.dto.Message;
import com.tx0x.nocalleridbackend.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public int createMessage(@RequestBody Message message) throws Exception {
        return messageService.createMessage(message);
    }

    @GetMapping(value = "/messages/{id}")
    public List<Message> getMessagesByUserId(@PathVariable("id") int id) {
        return messageService.getMessagesByUserId(id);
    }

    @GetMapping(value = "/messages")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @GetMapping(value = "/message/{id}")
    public Message getMessage(@PathVariable("id") int id) {
        return messageService.getMessage(id);
    }
}
