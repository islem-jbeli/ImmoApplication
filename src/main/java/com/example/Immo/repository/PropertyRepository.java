package com.example.Immo.repository;


import com.example.Immo.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByCity(String city); // ✅ ajout requis
}
