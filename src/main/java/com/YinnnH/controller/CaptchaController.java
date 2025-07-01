package com.YinnnH.controller;

import com.YinnnH.pojo.Captcha;
import com.YinnnH.pojo.Result;
import com.YinnnH.service.CaptchaService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.YinnnH.service.impl.CaptchaServiceImpl;
@Slf4j
@RestController
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;


    @GetMapping("/captcha")
    public Result getCaptcha(@RequestParam Captcha captcha) {
        captchaService.getCaptcha(captcha);
        return Result.success(captcha);

    }
}
