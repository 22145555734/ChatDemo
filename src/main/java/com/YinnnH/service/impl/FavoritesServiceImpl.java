package com.YinnnH.service.impl;

import com.YinnnH.mapper.FavoritesMapper;
import com.YinnnH.pojo.Favorites;
import com.YinnnH.pojo.Favorites_message;
import com.YinnnH.service.FavoritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;

    @Override
    public List<Favorites> favoritesServiceSelectAllFavorites(Favorites favorites) {
        try {
            log.info("开始查询所有收藏，传入参数: {}", favorites);
            List<Favorites> favoritesList = favoritesMapper.favoritesMapperSelectAllFavorites(favorites);
            log.info("成功查询到 {} 条收藏记录", favoritesList != null? favoritesList.size() : 0);
            return favoritesList;
        } catch (Exception e) {
            log.error("查询所有收藏时发生异常，传入参数: {}", favorites, e);
            return null;
        }
    }

    @Override
    public void InsertFavorites(Favorites favorites) {
        try {
            log.info("开始插入收藏，传入参数: {}", favorites);
            favoritesMapper.InsertFavorites(favorites);
            log.info("成功插入收藏记录");
        } catch (Exception e) {
            log.error("插入收藏时发生异常，传入参数: {}", favorites, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public void DeleteFavorites(Favorites favorites) {
        try {
            log.info("开始删除收藏，传入参数: {}", favorites);
            favoritesMapper.DeleteFavorites(favorites);
            log.info("成功删除收藏记录");
        } catch (Exception e) {
            log.error("删除收藏时发生异常，传入参数: {}", favorites, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public void DeleteFavorites2(Favorites favorites) {
        try {
            log.info("开始执行 DeleteFavorites2 操作，传入参数: {}", favorites);
            favoritesMapper.DeleteFavorites2(favorites);
            log.info("成功执行 DeleteFavorites2 操作");
        } catch (Exception e) {
            log.error("执行 DeleteFavorites2 操作时发生异常，传入参数: {}", favorites, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public List<Favorites_message> GetFavorites(Favorites_message favoritesMessage) {
        try {
            log.info("开始获取收藏消息，传入参数: {}", favoritesMessage);
            List<Favorites_message> result = favoritesMapper.GetFavorites(favoritesMessage);
            log.info("成功获取到 {} 条收藏消息记录", result != null? result.size() : 0);
            return result;
        } catch (Exception e) {
            log.error("获取收藏消息时发生异常，传入参数: {}", favoritesMessage, e);
            return null;
        }
    }
}
