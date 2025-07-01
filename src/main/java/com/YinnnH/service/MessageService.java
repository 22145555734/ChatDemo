package com.YinnnH.service;

import com.YinnnH.pojo.Message;

import java.util.List;

public interface MessageService {
    List<Message> messageServiceSellectAllMessage(Message message);

    void messageServiceInsertMessage(Message message);

    void messageServiceDeleteMessage(Message message);

    void DeleteMessageInFavorites(Integer id);

}
