package com.YinnnH.controller;

import com.YinnnH.pojo.Favorites_message;
import com.YinnnH.pojo.Result;
import com.YinnnH.service.FavoritesService;
import com.YinnnH.service.Favorites_messageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class Favorites_messageController {
    @Autowired
    private Favorites_messageService favoritesMessageService;


    @GetMapping("/favorites/message")
    public ResponseEntity<Result> getMessage(@RequestBody Favorites_message favoritesMessage) {
        try {
            log.info("开始处理获取消息请求，请求参数: {}", favoritesMessage);
            List<Favorites_message> favoritesMessageList = favoritesMessageService.GetMessage(favoritesMessage);
            log.info("成功获取消息，共获取到 {} 条消息", favoritesMessageList.size());
            Result result = Result.success(favoritesMessageList);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("获取消息时发生异常: ", e);
            Result result = Result.error("获取消息失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("favorites/message")
    public ResponseEntity<Result> insertMessage(@RequestBody Favorites_message favoritesMessage) {
        try {
            log.info("开始处理插入消息请求，请求参数: {}", favoritesMessage);
            favoritesMessageService.InsertMessage(favoritesMessage);
            log.info("消息插入成功");
            Result result = Result.success();
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("插入消息时发生异常: ", e);
            Result result = Result.error("插入消息失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @DeleteMapping("favorites/message")
    public Result deleteMessage(@Validated @RequestBody Favorites_message favoritesMessage) {
        try {
            favoritesMessageService.DeleteMessage(favoritesMessage);
            return Result.success();
        } catch (Exception e) {
            log.error("删除消息失败", e);
            return Result.error("删除消息失败，请稍后重试");
        }
    }


}
