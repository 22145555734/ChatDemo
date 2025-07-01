package com.YinnnH.service;

import com.YinnnH.pojo.Favorites;
import com.YinnnH.pojo.Favorites_message;

import java.util.List;

public interface FavoritesService {
    List<Favorites> favoritesServiceSelectAllFavorites(Favorites favorites);

    void InsertFavorites(Favorites favorites);

    void DeleteFavorites(Favorites favorites);

    void DeleteFavorites2(Favorites favorites);

    List<Favorites_message> GetFavorites(Favorites_message favoritesMessage);
}
