package com.example.Immo.services;

import com.example.Immo.entities.Messages;
import com.example.Immo.entities.User;
import com.example.Immo.repository.MessagesRepository; // ✅ Import manquant
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessagesRepository messagesRepository;

    public MessageServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<Messages> getAllMessages() {
        return messagesRepository.findAll();
    }

    @Override
    public List<Messages> getMessagesByReceiver(User receiver) {
        return messagesRepository.findByReceiver(receiver);
    }

    @Override
    public Messages saveMessage(User sender, User receiver, Messages message) {
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setSentDate(LocalDateTime.now());
        return messagesRepository.save(message);
    }
    @Override
    public Messages updateMessage(Long id, String newContent) {
        Messages message = messagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id " + id));
        message.setContent(newContent);
        return messagesRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messagesRepository.deleteById(id);
    }
}
