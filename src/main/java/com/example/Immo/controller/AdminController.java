package com.example.Immo.controller;

import com.example.Immo.entities.Admin;
import com.example.Immo.security.LoginRequest;
import com.example.Immo.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint login admin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Appel du service pour vérifier email et mot de passe
        Admin admin = adminService.loginAdmin(request.getEmail(), request.getMotDePasse());

        if (admin == null) {
            // Login échoué
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
        }

        // Login réussi
        return ResponseEntity.ok(admin);
    }
}
