package com.tkachuk.websocketproject.controller;

import com.tkachuk.websocketproject.model.MessageModel;
import com.tkachuk.websocketproject.model.UserStorage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageModel sendMessage(@Payload MessageModel message) {
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageModel addUser(@Payload MessageModel message,
                                SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String username = message.getSender();
        boolean isExists = UserStorage.getInstance().getUsers().contains(username);
        if (!isExists) {
            UserStorage.setUser(username);
            headerAccessor.getSessionAttributes().put("username", username);
        }
        else {
            throw new Exception("User already exists with username: " + username);
        }
        return message;
    }
}
