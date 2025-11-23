package com.example.Immo.services;

import com.example.Immo.entities.Property;
import com.example.Immo.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propRepo;

    public PropertyServiceImpl(PropertyRepository propRepo) {
        this.propRepo = propRepo;
    }

    @Override
    public Property saveProperty(Property p) {
        return propRepo.save(p);
    }

    @Override
    public List<Property> getAllProperties() {
        return propRepo.findAll();
    }

    @Override
    public Property getPropertyById(Long id) {
        return propRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Property with id " + id + " not found"));
    }

    @Override
    public void deleteProperty(Long id) {
        Property property = getPropertyById(id); // vérifie que la propriété existe
        propRepo.delete(property);
    }

    @Override
    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = getPropertyById(id);

        property.setTitle(propertyDetails.getTitle());
        property.setDescription(propertyDetails.getDescription());
        property.setCity(propertyDetails.getCity());
        property.setPrice(propertyDetails.getPrice());
        property.setImageUrl(propertyDetails.getImageUrl());
        property.setOwner(propertyDetails.getOwner());

        return propRepo.save(property);
    }

    @Override
    public List<Property> getPropertiesByCity(String city) {
        return propRepo.findByCity(city);
    }
}
