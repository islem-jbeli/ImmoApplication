package com.example.Immo.controller;

import com.example.Immo.entities.Favorite;
import com.example.Immo.services.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {



    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // Liste des favoris d'un utilisateur
    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable Long userId) {
        return favoriteService.findByUserId(userId);
    }

    // Ajouter un favori
    @PostMapping
    public Favorite addFavorite(@RequestParam Long userId, @RequestParam Long propertyId) {
        return favoriteService.save(new Favorite(userId, propertyId));
    }

    // Supprimer un favori
    @DeleteMapping("/{id}")
    public void deleteFavorite(@PathVariable Long id) {
        favoriteService.delete(id);
    }

    // Ajouter ou supprimer (toggle)
    @PostMapping("/toggle")
    public Favorite toggleFavorite(@RequestParam Long userId, @RequestParam Long propertyId) {
        return favoriteService.toggleFavorite(userId, propertyId);
    }
}
