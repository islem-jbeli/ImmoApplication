package com.example.Immo.entities;

import jakarta.persistence.*;
import com.example.Immo.entities.User;


@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String city;
    private double price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // Constructeurs
    public Property() {}

    public Property(String title, String description, String city, double price, User owner) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.price = price;
        this.owner = owner;
    }

    // Getters et Setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}