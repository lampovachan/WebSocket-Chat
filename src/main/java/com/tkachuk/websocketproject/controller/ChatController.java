package com.tkachuk.websocketproject.controller;

import com.tkachuk.websocketproject.model.MessageModel;
import com.tkachuk.websocketproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {
    @Autowired
    UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageModel sendMessage(@Payload MessageModel message) {
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageModel addUser(@Payload MessageModel message,
                                SimpMessageHeaderAccessor headerAccessor) throws Exception {
       userService.checkForUniqueness(message, headerAccessor);
       return message;
    }
}
