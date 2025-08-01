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
        return propRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProperty(Long id) {
        propRepo.deleteById(id);
    }

    @Override
    public List<Property> getPropertiesByCity(String city) {
        return propRepo.findByCity(city);
    }
}
