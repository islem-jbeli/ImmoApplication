package com.example.Immo.services;

import com.example.Immo.entities.Favorite;
import java.util.List;

public interface FavoriteService {
    Favorite save(Favorite favorite);            // Ajouter un favori
    List<Favorite> findByUserId(Long userId);    // Récupérer favoris d'un utilisateur
    void delete(Long id);                         // Supprimer un favori
    Favorite toggleFavorite(Long userId, Long propertyId); // Ajouter ou supprimer
}
