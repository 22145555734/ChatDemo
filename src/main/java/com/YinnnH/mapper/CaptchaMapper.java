package com.YinnnH.mapper;

import com.YinnnH.pojo.Captcha;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CaptchaMapper extends BaseMapper {


    @Select("SELECT FLOOR(100000 + RAND() * 900000) AS captcha;")
    Captcha getCapcha();
}
