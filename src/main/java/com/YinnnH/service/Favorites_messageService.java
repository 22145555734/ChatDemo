package com.YinnnH.service;

import com.YinnnH.pojo.Favorites_message;

import java.util.List;

public interface Favorites_messageService {
    List<Favorites_message> GetMessage(Favorites_message favoritesMessage);

    void InsertMessage(Favorites_message favoritesMessage);

    void DeleteMessage(Favorites_message favoritesMessage);
}
