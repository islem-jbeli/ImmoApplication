package com.example.Immo.controller;

import com.example.Immo.entities.User;
import com.example.Immo.security.JwtUtils;
import com.example.Immo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "*")
public class SecurityController {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    public SecurityController(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    /**
     * ✅ Vérifie si le token JWT est valide
     */
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        Map<String, Object> response = new HashMap<>();

        try {
            String email = jwtUtils.extractUsername(token);
            boolean isValid = jwtUtils.validateToken(token, email);

            response.put("valid", isValid);
            response.put("email", email);

            if (isValid) {
                User user = userService.getUserByEmail(email);
                response.put("role", user != null ? user.getRole() : null);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("valid", false);
            response.put("error", "Token invalide ou expiré");
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 👤 Récupère les informations de l'utilisateur connecté à partir du token
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestParam String token) {
        try {
            String email = jwtUtils.extractUsername(token);
            User user = userService.getUserByEmail(email);

            if (user == null) {
                return ResponseEntity.badRequest().body("Utilisateur non trouvé");
            }

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", user.getId());
            userInfo.put("nom", user.getNom());
            userInfo.put("prenom", user.getPrenom());
            userInfo.put("email", user.getEmail());
            userInfo.put("role", user.getRole());

            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Token invalide ou utilisateur introuvable");
        }
    }

    /**
     * 🧾 Test endpoint pour vérifier l’accès sécurisé
     */
    @GetMapping("/test")
    public ResponseEntity<String> testSecureEndpoint() {
        return ResponseEntity.ok("✅ Accès autorisé à la ressource sécurisée !");
    }
}

