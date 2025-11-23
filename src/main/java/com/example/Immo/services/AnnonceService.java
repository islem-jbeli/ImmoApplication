package com.example.Immo.services;


import com.example.Immo.entities.Annonce;
import com.example.Immo.entities.User;
import com.example.Immo.repository.AnnonceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnonceService {

    private final AnnonceRepository annonceRepository;

    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    public Optional<Annonce> getAnnonceById(Long id) {
        return annonceRepository.findById(id);
    }

    public Annonce createAnnonce(Annonce annonce) {
        // datePublication déjà gérée dans l'entité constructeur
        return annonceRepository.save(annonce);
    }

    public Annonce updateAnnonce(Long id, Annonce data) {
        return annonceRepository.findById(id).map(existing -> {
            existing.setTitre(data.getTitre());
            existing.setDescription(data.getDescription());
            existing.setVille(data.getVille());
            existing.setAdresse(data.getAdresse());
            existing.setPrix(data.getPrix());
            existing.setType(data.getType());
            existing.setSurface(data.getSurface());
            // ne change pas la datePublication ni le propriétaire par défaut
            return annonceRepository.save(existing);
        }).orElse(null);
    }

    public void deleteAnnonce(Long id) {
        annonceRepository.deleteById(id);
    }

    public List<Annonce> findByVille(String ville) {
        return annonceRepository.findByVille(ville);
    }

    public List<Annonce> findByPriceRange(double min, double max) {
        return annonceRepository.findByPrixBetween(min, max);
    }

    public Annonce validateAnnonce(Long id, boolean valide) {
        return annonceRepository.findById(id).map(a -> {
            a.setValidee(valide);
            return annonceRepository.save(a);
        }).orElse(null);
    }
}
