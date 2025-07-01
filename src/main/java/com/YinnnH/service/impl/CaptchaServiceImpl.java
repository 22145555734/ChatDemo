package com.YinnnH.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.YinnnH.mapper.CaptchaMapper;
import com.YinnnH.mapper.UserMapper;
import com.YinnnH.pojo.Captcha;
import com.YinnnH.pojo.Result;
import com.YinnnH.pojo.User;
import com.YinnnH.service.CaptchaService;
import com.YinnnH.utils.RegexUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
/**
 * @description 验证码服务实现类，实现 CaptchaService 接口，提供验证码相关业务逻辑的具体实现
 * @author [你的姓名]
 */
@Service
public class CaptchaServiceImpl extends ServiceImpl<UserMapper , User>implements CaptchaService {

    @Autowired
    private CaptchaMapper captchaMapper;


    @Override
    public Captcha getCaptcha(Captcha captcha) {
        try {
            // 调用 CaptchaMapper 的方法获取验证码相关信息
            Captcha resultCaptcha = captchaMapper.getCapcha();
            return resultCaptcha;
        } catch (Exception e) {
            // 捕获并处理异常，比如记录日志等
            // 打印异常信息
            e.printStackTrace();
            return null;
        }
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCaptcha(String email, HttpSession session) {
        //1.校验邮箱
        if(RegexUtils.isEmailInvalid(email)) {
            //2.如果不符合，返回错误信息
            return Result.error("非法的手机号格式");
        }
        //3.符合，生成验证码//hutool
        String captcha = RandomUtil.randomNumbers(6);

        //4.保存验证码到redis
        stringRedisTemplate.opsForValue().set("login:captcha:" + email, captcha, 2, TimeUnit.MINUTES);

        //5.发送验证码
        log.info("发送短信验证码成功：{}", captcha);
        return Result.success();
    }
}