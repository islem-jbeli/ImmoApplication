package com.example.Immo.services;

import com.example.Immo.entities.Property;
import java.util.List;

public interface PropertyService {
    Property saveProperty(Property property);
    List<Property> getAllProperties();
    Property getPropertyById(Long id);
    void deleteProperty(Long id);
    List<Property> getPropertiesByCity(String city);
}