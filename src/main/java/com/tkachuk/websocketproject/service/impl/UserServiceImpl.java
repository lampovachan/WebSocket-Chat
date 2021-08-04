package com.tkachuk.websocketproject.service.impl;

import com.tkachuk.websocketproject.model.MessageModel;
import com.tkachuk.websocketproject.service.UserService;
import com.tkachuk.websocketproject.storage.UserStorage;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public void checkForUniqueness(MessageModel message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String username = message.getSender();
        boolean isExists = UserStorage.getInstance().getUsers().contains(username);
        if (!isExists) {
            UserStorage.setUser(username);
            headerAccessor.getSessionAttributes().put("username", username);
        }
        else {
            throw new Exception("User already exists with username: " + username);
        }
    }
}
