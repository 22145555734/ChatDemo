package com.YinnnH.mapper;

import com.YinnnH.pojo.Favorites;
import com.YinnnH.pojo.Favorites_message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoritesMapper extends BaseMapper {

    @Select("select * from favorites where username = #{username}")
    List<Favorites> favoritesMapperSelectAllFavorites(Favorites favorites);

    @Insert("INSERT INTO favorites(username, favorites_name) VALUES (#{username}, #{favorites_name})")
    void InsertFavorites(Favorites favorites);


    @Delete("delete from favorites where favorites_name = #{favorites_name}")
    void DeleteFavorites(Favorites favorites);

    @Delete("delete from favorites_message where favorites_name = #{favorites_name}")
    void DeleteFavorites2(Favorites favorites);

    @Select("SELECT * FROM favorites_message WHERE favorites_name = #{favorites_name}")
    List<Favorites_message> GetFavorites(Favorites_message favoritesMessage);


}
