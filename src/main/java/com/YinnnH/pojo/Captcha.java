package com.YinnnH.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Captcha {
    private Integer id;
    private String email;
    private int captcha;

    public void generateCaptcha() {
        SecureRandom secureRandom = new SecureRandom();
        // 生成 100000 到 999999 之间的随机整数
        this.captcha = secureRandom.nextInt(900000) + 100000;
    }
}