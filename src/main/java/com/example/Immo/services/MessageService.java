package com.example.Immo.services;

import com.example.Immo.entities.Messages;
import com.example.Immo.entities.User;
import java.util.List;

public interface MessageService {
    List<Messages> getAllMessages();
    List<Messages> getMessagesByReceiver(User receiver);
    Messages saveMessage(User sender, User receiver, Messages message);
    void deleteMessage(Long id);
    Messages updateMessage(Long id, String newContent);

}
