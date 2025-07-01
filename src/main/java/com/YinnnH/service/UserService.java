package com.YinnnH.service;

import com.YinnnH.pojo.LoginFormDTO;
import com.YinnnH.pojo.PageBean;
import com.YinnnH.pojo.Result;
import com.YinnnH.pojo.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    void userServiceRegister(User user);

    User userServiceLogIn(User user);

    void userServiceUpdatePassword(User user);

    void userServiceUpdateInformation(User user);

    User userServiceSelectByName(User user);

    void userServiceDelete(User user);

    PageBean selectPage(Integer page, Integer pageSize);

    Result login(LoginFormDTO loginFormDTO, HttpSession httpSession);
}
