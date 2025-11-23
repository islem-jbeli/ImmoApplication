package com.example.Immo.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "annonces")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(length = 2000)
    private String description;

    private String ville;
    private String adresse;
    private double prix;
    private String type; // Appartement, Maison, Terrain...
    private double surface;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePublication;

    private boolean validee = false;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    private User proprietaire;

    public Annonce() {
        this.datePublication = new Date();
    }

    // Constructeur utile
    public Annonce(String titre, String description, String ville, String adresse,
                   double prix, String type, double surface, User proprietaire) {
        this.titre = titre;
        this.description = description;
        this.ville = ville;
        this.adresse = adresse;
        this.prix = prix;
        this.type = type;
        this.surface = surface;
        this.proprietaire = proprietaire;
        this.datePublication = new Date();
    }

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getSurface() { return surface; }
    public void setSurface(double surface) { this.surface = surface; }

    public Date getDatePublication() { return datePublication; }
    public void setDatePublication(Date datePublication) { this.datePublication = datePublication; }

    public boolean isValidee() { return validee; }
    public void setValidee(boolean validee) { this.validee = validee; }

    public User getProprietaire() { return proprietaire; }
    public void setProprietaire(User proprietaire) { this.proprietaire = proprietaire; }
}
