package com.YinnnH.controller;

import com.YinnnH.pojo.Result;
import com.YinnnH.pojo.User;
import com.YinnnH.pojo.PageBean;
import com.YinnnH.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/user/register")
    public ResponseEntity<Result> insert(@RequestBody User user) {
        try {
            log.info("新增员工user : {}", user);
            userService.userServiceRegister(user);
            log.info("员工新增成功");
            Result result = Result.success();
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("新增员工时发生异常: ", e);
            Result result = Result.error("新增员工失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/password")
    public ResponseEntity<Result> update_password(@RequestBody User user) {
        try {
            log.info("{}修改密码", user);
            userService.userServiceUpdatePassword(user);
            log.info("密码修改成功");
            Result result = Result.success(user.getPassword());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("修改密码时发生异常: ", e);
            Result result = Result.error("修改密码失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/information")
    public ResponseEntity<Result> update_information(@RequestBody User user) {
        try {
            log.info("{}修改信息", user);
            userService.userServiceUpdateInformation(user);
            log.info("信息修改成功");
            Result result = Result.success(user);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("修改信息时发生异常: ", e);
            Result result = Result.error("修改信息失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/information")
    public ResponseEntity<Result> SelectById(@RequestBody User user) {
        try {
            log.info("查看{}的信息", user);
            User user1 = userService.userServiceSelectByName(user);
            if (user1 != null) {
                log.info("成功获取信息");
                Result result = Result.success(user1);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                log.warn("未找到对应信息");
                Result result = Result.error("未找到对应信息");
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("查询信息时发生异常: ", e);
            Result result = Result.error("查询信息失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<Result> Delete(@RequestBody User user) {
        try {
            log.info("删除{}数据", user.getName());
            userService.userServiceDelete(user);
            log.info("数据删除成功");
            Result result = Result.success();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("删除数据时发生异常: ", e);
            Result result = Result.error("删除数据失败，请稍后重试");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/message/page")
    public Result selectByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("第{}页消息，每页{}条", page, pageSize);
        try {
            PageBean pageBean = userService.selectPage(page, pageSize);
            return Result.success(pageBean);
        } catch (Exception e) {
            log.error("分页查询消息时发生异常", e);
            return Result.error("分页查询消息失败，请稍后重试");
        }
    }







    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        Result result = new Result();
            session.setAttribute("user", user.getName());
        return result;
    }

    @GetMapping("/getUsername")
    public String getUsername(HttpSession session) {
        String username = (String) session.getAttribute("user");
        return username;
    }


}
