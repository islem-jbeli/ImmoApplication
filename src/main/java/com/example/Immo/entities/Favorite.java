package com.example.Immo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;       // ID de l'utilisateur connecté
    private Long propertyId;   // ID de la propriété

    // Constructeurs
    public Favorite() {}

    public Favorite(Long userId, Long propertyId) {
        this.userId = userId;
        this.propertyId = propertyId;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getPropertyId() { return propertyId; }
    public void setPropertyId(Long propertyId) { this.propertyId = propertyId; }
}
