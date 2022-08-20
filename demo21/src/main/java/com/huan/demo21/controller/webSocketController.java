package com.huan.demo21.controller;

import com.huan.demo21.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class webSocketController {
/*    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message){
        return message;
    }*/
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/hello")
    public void greeting(Message message){
        simpMessagingTemplate.convertAndSend("/topic/greetings",message);
    }
}
