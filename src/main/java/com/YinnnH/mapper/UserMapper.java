package com.YinnnH.mapper;

import com.YinnnH.pojo.Message;
import com.YinnnH.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("insert into user (name, gender, email, password, create_time)" +
            " VALUES (#{name}, #{gender},#{email},#{password},#{createTime})")
    void userMapperRegister(User user);


    @Select("select * from user where email = #{email} and password = #{password}")
    User userMapperLogIn(User user);


    @Update("update user set password = #{password} WHERE name = #{name}")
    void userMapperUpdatePassword(User user);

    @Update("UPDATE user set gender = #{gender},email = #{email} WHERE id = #{id}")
    void userMapperUpdateInformation(User user);

    @Select("SELECT id,name,gender,email,create_time from user where name = #{name}")
    User userMapperSelectByName(User user);



    @Delete("delete from user WHERE email = #{email} and password = #{password}")
    void userMapperDelect(User user);

    @Select("select * from message")
    List<Message> userMapperPage();

    @Insert("insert into user (name, gender, email, password, create_time)" +
            " VALUES (#{name}, #{gender},#{email},#{password},#{createTime})")
    void Insert(User user);
}
