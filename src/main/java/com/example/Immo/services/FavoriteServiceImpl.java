package com.example.Immo.services;

import com.example.Immo.entities.Favorite;
import com.example.Immo.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public List<Favorite> findByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    @Override
    public void delete(Long id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public Favorite toggleFavorite(Long userId, Long propertyId) {
        return favoriteRepository.findByUserIdAndPropertyId(userId, propertyId)
                .map(fav -> {
                    favoriteRepository.delete(fav);
                    return fav; // retourner l'objet supprimé
                })
                .orElseGet(() -> favoriteRepository.save(new Favorite(userId, propertyId)));
    }

}
