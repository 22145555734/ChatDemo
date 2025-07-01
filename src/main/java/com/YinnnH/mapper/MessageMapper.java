package com.YinnnH.mapper;

import com.YinnnH.pojo.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper {
    @Select("SELECT * from message where username = #{username}")
    List<Message> messageMapperSellectAllMessage(Message message);

    @Insert("INSERT into message (username, message) VALUES (#{username}, #{message})")
    void messageMapperInsertMessage(Message message);

    @Delete("DELETE from message where username = #{username} and message = #{message}")
    void messageMapperDeleteMessage(Message message);

    @Delete("DELETE FROM favorites_message where message = #{message}")
    void DeleteMessageInFavorites(Integer id);
}
