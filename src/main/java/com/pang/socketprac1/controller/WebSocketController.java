package com.pang.socketprac1.controller;

import com.pang.socketprac1.dto.Message;
import com.pang.socketprac1.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class WebSocketController {

    @Autowired
    private WebSocketService service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message){
        service.notifyFrontEnd(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable String id,
            @RequestBody Message message){
        service.notifyUser(id, message.getMessageContent());
    }
}
