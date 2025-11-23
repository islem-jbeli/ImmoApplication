package com.example.Immo.controller;

import com.example.Immo.entities.User;
import com.example.Immo.services.UserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🟢 CREATE : Ajouter un utilisateur
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️ Email requis !");
        }

        User existing = userService.getUserByEmail(user.getEmail());
        if (existing != null) {
            return ResponseEntity.badRequest().body("⚠️ Email déjà utilisé !");
        }

        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // 🟢 READ : Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 🟢 READ : Récupérer par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(404).body("⚠️ Utilisateur introuvable");
    }

    // 🟢 READ : Récupérer par email
    @GetMapping("/by-email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);

        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(404).body("⚠️ Aucun utilisateur trouvé avec cet email");
    }

    // 🟠 UPDATE : Modifier un utilisateur
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updated = userService.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("⚠️ Utilisateur introuvable");
        }
    }

    // 🔴 DELETE : Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);

        return deleted
                ? ResponseEntity.ok("🗑️ Utilisateur supprimé avec succès")
                : ResponseEntity.status(404).body("⚠️ Utilisateur introuvable");
    }

    // ✅ Test rapide pour vérifier que le controller répond
    @GetMapping("/test")
    public String test() {
        return "OK";
    }
}
