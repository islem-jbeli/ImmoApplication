package com.example.Immo.services;

import com.example.Immo.entities.User;
import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    boolean deleteUser(Long id);

    // 🔥 LA MÉTHODE QUI MANQUAIT !
    User updateUser(Long id, User user);
}
