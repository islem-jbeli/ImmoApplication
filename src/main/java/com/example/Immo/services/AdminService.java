package com.example.Immo.services;

import com.example.Immo.entities.Admin;
import com.example.Immo.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Méthode pour vérifier email et mot de passe
    public Admin loginAdmin(String email, String motDePasse) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getMotDePasse().equals(motDePasse)) {
            return admin; // login réussi
        }
        return null; // login échoué
    }
}
