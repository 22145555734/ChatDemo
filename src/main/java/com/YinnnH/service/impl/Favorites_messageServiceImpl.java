package com.YinnnH.service.impl;

import com.YinnnH.mapper.FavoritesMapper;
import com.YinnnH.mapper.Favorites_messageMapper;
import com.YinnnH.pojo.Favorites_message;
import com.YinnnH.service.Favorites_messageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class Favorites_messageServiceImpl implements Favorites_messageService {
    @Autowired
    private Favorites_messageMapper favoritesMessageMapper;

    @Override
    public List<Favorites_message> GetMessage(Favorites_message favoritesMessage) {
        try {
            List<Favorites_message> favoritesMessageList = favoritesMessageMapper.GetMassage(favoritesMessage);
            return favoritesMessageList;
        } catch (Exception e) {
            log.error("调用 GetMassage 方法获取收藏消息时发生异常", e);
            return null;
        }
    }

    @Override
    public void InsertMessage(Favorites_message favoritesMessage) {
        try {
            favoritesMessageMapper.InsertMessage(favoritesMessage);
        } catch (Exception e) {
            log.error("调用 InsertMessage 方法插入收藏消息时发生异常", e);
        }
    }

    @Override
    public void DeleteMessage(Favorites_message favoritesMessage) {
        try {
            favoritesMessageMapper.DeleteMessage(favoritesMessage);
        } catch (Exception e) {
            log.error("调用 DeleteMessage 方法删除收藏消息时发生异常", e);
        }
    }
}
