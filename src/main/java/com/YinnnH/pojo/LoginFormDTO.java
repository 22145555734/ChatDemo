package com.YinnnH.pojo;

import lombok.Data;

@Data
public class LoginFormDTO {
    private String email;
    private String captcha;
    private String password;
}
