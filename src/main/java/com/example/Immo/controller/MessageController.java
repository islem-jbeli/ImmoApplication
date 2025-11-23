package com.example.Immo.controller;

import com.example.Immo.entities.Messages;
import com.example.Immo.entities.User;
import com.example.Immo.services.MessageService;
import com.example.Immo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    // 🔹 Récupérer tous les messages
    @GetMapping
    public List<Messages> getAllMessages() {
        return messageService.getAllMessages();
    }

    // 🔹 Envoyer un message entre deux utilisateurs via PathVariables
    @PostMapping("/send/{senderId}/{receiverId}")
    public Messages sendMessage(
            @PathVariable Long senderId,
            @PathVariable Long receiverId,
            @RequestBody Messages message
    ) {
        User sender = userService.getUserById(senderId);
        User receiver = userService.getUserById(receiverId);
        return messageService.saveMessage(sender, receiver, message);
    }

    // 🔹 Envoyer un message en remplissant le JSON avec sender et receiver
    @PostMapping
    public Messages createMessage(@RequestBody Messages message) {
        if (message.getSender() == null || message.getReceiver() == null) {
            throw new RuntimeException("Sender and receiver must be provided");
        }
        User sender = userService.getUserById(message.getSender().getId());
        User receiver = userService.getUserById(message.getReceiver().getId());
        return messageService.saveMessage(sender, receiver, message);
    }

    // 🔹 Récupérer les messages reçus par un utilisateur
    @GetMapping("/received/{userId}")
    public List<Messages> getReceivedMessages(@PathVariable Long userId) {
        User receiver = userService.getUserById(userId);
        return messageService.getMessagesByReceiver(receiver);
    }
    // 🔹 Mettre à jour le contenu d'un message
    @PutMapping("/{id}")
    public Messages updateMessage(
            @PathVariable Long id,
            @RequestBody Messages updatedMessage
    ) {
        return messageService.updateMessage(id, updatedMessage.getContent());
    }

    // 🔹 Supprimer un message par ID
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
