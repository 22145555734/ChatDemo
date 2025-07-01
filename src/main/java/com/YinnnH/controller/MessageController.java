package com.YinnnH.controller;

import com.YinnnH.pojo.Message;
import com.YinnnH.pojo.Result;
import com.YinnnH.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("user/message")
    public ResponseEntity<Result> SelectAllMessage(@RequestBody Message message) {
        try {
            log.info("查询 {} 聊天记录", message.getUsername());
            List<Message> messages = messageService.messageServiceSellectAllMessage(message);
            log.info("成功查询到 {} 条聊天记录", messages.size());
            Result result = Result.success(messages);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("查询聊天记录时发生异常: ", e);
            Result result = Result.error("查询聊天记录失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/message")
    public ResponseEntity<Result> InsertMessage(@RequestBody Message message) {
        try {
            log.info("{} 发送消息: {}", message.getUsername(), message.getMessage());
            messageService.messageServiceInsertMessage(message);
            log.info("消息发送成功");
            Result result = Result.success();
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("发送消息时发生异常: ", e);
            Result result = Result.error("发送消息失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/message")
    public ResponseEntity<Result> DeleteMessage(@RequestBody Message message) {
        try {
            log.info("{} 删除消息: {}", message.getUsername(), message.getMessage());
            messageService.messageServiceDeleteMessage(message);
            messageService.DeleteMessageInFavorites(message.getId());
            log.info("消息删除成功");
            Result result = Result.success();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除消息时发生异常: ", e);
            Result result = Result.error("删除消息失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
