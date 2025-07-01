package com.YinnnH.service;

import com.YinnnH.pojo.Captcha;
import com.YinnnH.pojo.Result;
import jakarta.servlet.http.HttpSession;

/**
 * @description 验证码服务接口，提供与验证码相关的业务操作方法
 * @author [你的姓名]
 */
public interface CaptchaService {

    /**
     * 根据传入的 Captcha 对象 email
     * 包含生成新的验证码操作。
     *
     * @param captcha 包含验证码相关信息（如邮箱、用户标识等）的 Captcha 对象
     * @return 包含获取到的验证码信息的 Captcha 对象
     */
    Captcha getCaptcha(Captcha captcha);

    Result sendCaptcha(String email, HttpSession session);
}