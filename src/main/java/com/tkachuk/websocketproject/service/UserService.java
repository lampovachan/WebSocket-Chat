package com.tkachuk.websocketproject.service;


import com.tkachuk.websocketproject.model.MessageModel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface UserService {
    public void checkForUniqueness(MessageModel message, SimpMessageHeaderAccessor headerAccessor) throws Exception;
}
