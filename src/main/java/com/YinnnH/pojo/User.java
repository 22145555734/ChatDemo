package com.YinnnH.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    private Integer id;
    private String name;
    private String gender;
    private String email;
    private String password;
    private LocalDateTime createTime;
    private Integer captcha;
}
