package com.YinnnH.controller;

import com.YinnnH.pojo.LoginFormDTO;
import com.YinnnH.pojo.Result;
import com.YinnnH.pojo.User;
import com.YinnnH.service.UserService;
import com.YinnnH.utils.JwtUtils;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user) {
        try {
            log.info("员工{}登录", user);
            User e = userService.userServiceLogIn(user);

            //如果登录成功需要生成令牌并下发令牌
            if (e != null) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("email", e.getEmail());
                claims.put("password", e.getPassword());
                String jwt = JwtUtils.generateJwt(claims);
                if (jwt != null) {
                    return Result.success(jwt);
                } else {
                    // JWT生成失败的情况
                    log.error("JWT生成失败");
                    return Result.error("JWT生成失败，请稍后重试");
                }
            }

            //如果登录失败需要返回错误信息
            return Result.error("账号或密码错误");
        } catch (Exception ex) {
            // 捕获登录过程中可能出现的异常
            log.error("登录过程中出现异常：", ex);
            return Result.error("登录过程中出现异常，请稍后重试");
        }
    }


    @PostMapping("/user/login/captcha")
    public Result login(@RequestBody LoginFormDTO loginFormDTO, HttpSession httpSession) {

        return userService.login(loginFormDTO, httpSession);
    }
}