package com.example.Immo.repository;


import com.example.Immo.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findByVille(String ville);
    List<Annonce> findByPrixBetween(double min, double max);
    List<Annonce> findByValidee(boolean validee);
}
