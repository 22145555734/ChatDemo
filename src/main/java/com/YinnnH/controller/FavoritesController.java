package com.YinnnH.controller;

import com.YinnnH.pojo.Favorites;
import com.YinnnH.pojo.Favorites_message;
import com.YinnnH.pojo.Result;
import com.YinnnH.service.FavoritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;


    @GetMapping("/user/favorites")
    public ResponseEntity<Result> selectAllFavorites(@RequestBody Favorites favorites) {
        try {
            log.info("查询用户 {} 收藏夹", favorites.getUsername());
            List<Favorites> favoritesList = favoritesService.favoritesServiceSelectAllFavorites(favorites);
            log.info("成功查询到 {} 个收藏夹", favoritesList.size());
            Result result = Result.success(favoritesList);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("查询用户收藏夹时发生异常: ", e);
            Result result = Result.error("查询用户收藏夹失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/favorites")
    public ResponseEntity<Result> insertFavorites(@RequestBody Favorites favorites) {
        try {
            log.info("{} 新建收藏夹: {}", favorites.getUsername(), favorites.getFavorites_name());
            favoritesService.InsertFavorites(favorites);
            log.info("收藏夹 {} 新建成功", favorites.getFavorites_name());
            Result result = Result.success(favorites);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("新建收藏夹时发生异常: ", e);
            Result result = Result.error("新建收藏夹失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/favorites")
    public ResponseEntity<Result> deleteFavorites(@RequestBody Favorites favorites) {
        try {
            log.info("删除收藏夹 {}", favorites.getFavorites_name());
            favoritesService.DeleteFavorites(favorites);
            favoritesService.DeleteFavorites2(favorites);
            log.info("收藏夹 {} 删除成功", favorites.getFavorites_name());
            Result result = Result.success(favorites);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除收藏夹时发生异常: ", e);
            Result result = Result.error("删除收藏夹失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/favorites")
    public ResponseEntity<Result> getFavorites(@RequestBody Favorites_message favoritesMessage) {
        try {
            log.info("查看的 {} 收藏夹", favoritesMessage.getFavorites_name());
            List<Favorites_message> favoritesMessageList = favoritesService.GetFavorites(favoritesMessage);
            log.info("成功获取到 {} 条收藏夹消息", favoritesMessageList.size());
            Result result = Result.success(favoritesMessageList);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取收藏夹消息时发生异常: ", e);
            Result result = Result.error("获取收藏夹消息失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
