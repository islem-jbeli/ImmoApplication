package com.example.Immo.controller;

import com.example.Immo.entities.Property;
import com.example.Immo.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * Crée un nouveau bien immobilier
     */
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property createdProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok(createdProperty);
    }

    /**
     * Récupère tous les biens
     */
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    /**
     * Récupère un bien par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        return (property != null) ? ResponseEntity.ok(property) : ResponseEntity.notFound().build();
    }

    /**
     * Supprime un bien par ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupère les biens d'une ville spécifique
     */
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Property>> getPropertiesByCity(@PathVariable String city) {
        List<Property> properties = propertyService.getPropertiesByCity(city);
        return ResponseEntity.ok(properties);
    }
}
