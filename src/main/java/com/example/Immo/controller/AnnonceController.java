package com.example.Immo.controller;

import com.example.Immo.entities.Annonce;
import com.example.Immo.entities.User;
import com.example.Immo.services.AnnonceService;
import com.example.Immo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonces")
@CrossOrigin(origins = "*")
public class AnnonceController {

    private final AnnonceService annonceService;
    private final UserService userService;

    public AnnonceController(AnnonceService annonceService, UserService userService) {
        this.annonceService = annonceService;
        this.userService = userService;
    }

    // 🔹 Récupérer toutes les annonces
    @GetMapping
    public List<Annonce> getAll() {
        return annonceService.getAllAnnonces();
    }

    // 🔹 Récupérer une annonce par ID
    @GetMapping("/{id}")
    public ResponseEntity<Annonce> getById(@PathVariable Long id) {
        return annonceService.getAnnonceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Créer une annonce pour un propriétaire
    @PostMapping("/create/{ownerId}")
    public ResponseEntity<?> createAnnonce(@PathVariable Long ownerId, @RequestBody Annonce annonce) {
        User owner = userService.getUserById(ownerId);
        if (owner == null) {
            return ResponseEntity.badRequest().body("Propriétaire introuvable");
        }
        annonce.setProprietaire(owner);
        Annonce saved = annonceService.createAnnonce(annonce);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Mettre à jour une annonce
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnonce(@PathVariable Long id, @RequestBody Annonce annonce) {
        Annonce updated = annonceService.updateAnnonce(id, annonce);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // 🔹 Supprimer une annonce
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.ok().build();
    }

    // 🔹 Filtrer par ville
    @GetMapping("/ville/{ville}")
    public List<Annonce> getByVille(@PathVariable String ville) {
        return annonceService.findByVille(ville);
    }

    // 🔹 Filtrer par prix
    @GetMapping("/prix")
    public List<Annonce> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return annonceService.findByPriceRange(min, max);
    }

    // 🔹 Validation par admin
    @PutMapping("/validate/{id}")
    public ResponseEntity<?> validateAnnonce(@PathVariable Long id, @RequestParam boolean valide) {
        Annonce a = annonceService.validateAnnonce(id, valide);
        if (a == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(a);
    }
}
