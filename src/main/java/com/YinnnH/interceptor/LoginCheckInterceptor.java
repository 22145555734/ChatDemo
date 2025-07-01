package com.YinnnH.interceptor;

import ch.qos.logback.core.util.StringUtil;
import com.YinnnH.pojo.Result;
import com.YinnnH.utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//用于登录校验
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //1.获取请求的URL
        String url = req.getRequestURI().toString();
        log.info("请求的url:{}", url);


        //2.判断请求是否包含login, 如果是包含则放行
        if(url.contains("login")) {
            log.info("登录操作,放行");
            return true;
        }


        //3.获取请求头中的令牌token
        String jwt = req.getHeader("token");


        //4.判断令牌是否存在,如果不存在则返回未登录的信息
        if(!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空, 返回未登录的信息");
            Result error = Result.error("Not_LoGin");
            //手动转换对象 json
            String notLogin = JSON.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;

        }


        //5.解析token如果解析失败则返回未登录
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录的错误信息");
            Result error = Result.error("Not_LoGin");
            String notLogin = JSON.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }


        //6.放行
        log.info("令牌合法");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle已执行");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion已执行");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
