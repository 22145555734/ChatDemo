package com.YinnnH.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.YinnnH.mapper.UserMapper;
import com.YinnnH.pojo.*;
import com.YinnnH.service.UserService;
import com.YinnnH.utils.RegexUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void userServiceRegister(User user) {
        try {
            user.setCreateTime(LocalDateTime.now());
            log.info("开始注册用户，传入参数: {}", user);
            userMapper.userMapperRegister(user);
            log.info("成功注册用户");
        } catch (Exception e) {
            log.error("用户注册时发生异常，传入参数: {}", user, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public User userServiceLogIn(User user) {
        try {
            log.info("开始用户登录，传入参数: {}", user);
            User result = userMapper.userMapperLogIn(user);
            log.info("用户登录操作完成，返回结果: {}", result);
            return result;
        } catch (Exception e) {
            log.error("用户登录时发生异常，传入参数: {}", user, e);
            return null;
        }
    }

    @Override
    public void userServiceUpdatePassword(User user) {
        try {
            log.info("开始更新用户密码，传入参数: {}", user);
            userMapper.userMapperUpdatePassword(user);
            log.info("成功更新用户密码");
        } catch (Exception e) {
            log.error("更新用户密码时发生异常，传入参数: {}", user, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public void userServiceUpdateInformation(User user) {
        try {
            log.info("开始更新用户信息，传入参数: {}", user);
            userMapper.userMapperUpdateInformation(user);
            log.info("成功更新用户信息");
        } catch (Exception e) {
            log.error("更新用户信息时发生异常，传入参数: {}", user, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public User userServiceSelectByName(User user) {
        try {
            log.info("开始根据用户名查询用户，传入参数: {}", user);
            User user1 = userMapper.userMapperSelectByName(user);
            log.info("根据用户名查询用户完成，返回结果: {}", user1);
            return user1;
        } catch (Exception e) {
            log.error("根据用户名查询用户时发生异常，传入参数: {}", user, e);
            return null;
        }
    }

    @Override
    public void userServiceDelete(User user) {
        try {
            log.info("开始删除用户，传入参数: {}", user);
            userMapper.userMapperDelect(user);
            log.info("成功删除用户");
        } catch (Exception e) {
            log.error("删除用户时发生异常，传入参数: {}", user, e);
            // 根据业务需求决定是否抛出异常或进行其他处理
        }
    }

    @Override
    public PageBean selectPage(Integer page, Integer pageSize) {
        PageHelper .startPage(page, pageSize);
        List<Message> messages = userMapper.userMapperPage();
        Page<Message> p = (Page<Message>) messages;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Result login(LoginFormDTO loginFormDTO, HttpSession session) {
        //1.校验手机号
        String email = loginFormDTO.getEmail();
        if(RegexUtils.isEmailInvalid(email)) {
            //2.如果不符合，返回错误信息
            return Result.error("非法的邮箱格式");
        }
//        //3.从redis校验验证码
//        String captcha1 = stringRedisTemplate.opsForValue().get("login:captcha:" + email);
//        System.out.println(captcha1);
//        String captcha2 = loginFormDTO.getCaptcha();
//        if (captcha1 == null || !captcha1.equals(captcha2)) {
//            //不一致，报错
//            return Result.error("验证码错误");
//        }
        //4.一致，查询用户
        User user = query().eq("email", email).one();
        //5.判断用户是否存在
        if (user == null) {
            //6.不存在
            user = creatUserWithEmail(email);
        }
        //7.保存用户信息到redis

        //7.1生成token
        String token = UUID.randomUUID().toString(true);
        //7.1将User转为哈希
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO);
        //7.3存储
        redisTemplate.opsForHash().putAll("login:token:" + token, userMap);
        //7.4设置token有效期
        redisTemplate.expire("login:token:" + token, 30, TimeUnit.MINUTES);

        //8.返回token
        //userMapper.Insert(user);
        return Result.success(token);
    }

    private User creatUserWithEmail(String email) {
        //1.创建用户
        User user = new User();
        user.setEmail(email);
        //2.随机name
        user.setName("user_"+RandomUtil.randomString(5));
        save(user);
        return user;
    }
}
