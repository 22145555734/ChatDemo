package com.YinnnH.service.impl;

import com.YinnnH.mapper.MessageMapper;
import com.YinnnH.pojo.Message;
import com.YinnnH.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> messageServiceSellectAllMessage(Message message) {
        try {
            log.info("开始查询所有消息，传入参数: {}", message);
            List<Message> messages = messageMapper.messageMapperSellectAllMessage(message);
            log.info("成功查询到 {} 条消息记录", messages != null? messages.size() : 0);
            return messages;
        } catch (Exception e) {
            log.error("查询所有消息时发生异常，传入参数: {}", message, e);
            return null;
        }
    }

    @Override
    public void messageServiceInsertMessage(Message message) {
        try {
            log.info("开始插入消息，传入参数: {}", message);
            messageMapper.messageMapperInsertMessage(message);
            log.info("成功插入消息记录");
        } catch (Exception e) {
            log.error("插入消息时发生异常，传入参数: {}", message, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public void messageServiceDeleteMessage(Message message) {
        try {
            log.info("开始删除消息，传入参数: {}", message);
            messageMapper.messageMapperDeleteMessage(message);
            log.info("成功删除消息记录");
        } catch (Exception e) {
            log.error("删除消息时发生异常，传入参数: {}", message, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public void DeleteMessageInFavorites(Integer id) {
        try {
            messageMapper.DeleteMessageInFavorites(id);
        } catch (Exception e) {
            log.error("删除消息时发生异常，传入参数: {}", id, e);
            System.err.println("删除收藏消息失败，请检查相关配置和数据");
        }
    }
}
