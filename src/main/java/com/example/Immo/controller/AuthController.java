package com.example.Immo.controller;

import com.example.Immo.entities.User;
import com.example.Immo.security.JwtResponse;
import com.example.Immo.security.JwtUtils;
import com.example.Immo.security.LoginRequest;
import com.example.Immo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtils jwtUtils, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Enregistrement
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("❌ Cet email est déjà utilisé !");
        }
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("✅ Utilisateur enregistré avec succès : " + createdUser.getEmail());
    }

    // 🔐 Connexion
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // 1️⃣ Vérifier si l'utilisateur existe
        User user = userService.getUserByEmail(loginRequest.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("❌ Utilisateur non trouvé");
        }

        // 2️⃣ Vérifier le mot de passe
        if (!passwordEncoder.matches(loginRequest.getMotDePasse(), user.getMotDePasse())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("❌ Mot de passe incorrect");
        }

        // 3️⃣ Générer le token JWT
        String token = jwtUtils.generateJwtToken(user.getEmail());

        // 4️⃣ Récupérer le rôle réel de l'utilisateur
        String role = user.getRole(); // Assurez-vous que User a un champ 'role'

        // 5️⃣ Retourner la réponse avec le token
        JwtResponse jwtResponse = new JwtResponse(token, user.getEmail(), role);
        return ResponseEntity.ok(jwtResponse);
    }
}
