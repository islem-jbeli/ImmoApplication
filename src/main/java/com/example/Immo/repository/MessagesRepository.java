package com.example.Immo.repository;

import com.example.Immo.entities.Messages;
import com.example.Immo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
    List<Messages> findByReceiver(User receiver);
}
