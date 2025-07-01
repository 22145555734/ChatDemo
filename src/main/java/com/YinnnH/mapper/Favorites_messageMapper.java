package com.YinnnH.mapper;

import com.YinnnH.pojo.Favorites_message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Favorites_messageMapper extends BaseMapper {

    @Select("SELECT * FROM favorites_message WHERE favorites_name = #{favorites_name}")
    List<Favorites_message> GetMassage(Favorites_message favoritesMessage);

    @Insert("INSERT INTO favorites_message (favorites_name, message_id, message)" +
            "VALUES (#{favorites_name}, #{message_id}, #{message})")
    void InsertMessage(Favorites_message favoritesMessage);

    @Delete("delete from favorites_message where message_id = #{message_id} and favorites_name = #{favorites_name}")
    void DeleteMessage(Favorites_message favoritesMessage);
}
