package com.jfeng.chapter4.controller;

import com.jfeng.chapter4.aspect.recordrunningtime.RecordRunningTime;
import com.jfeng.framework.mvc.annotation.Action;
import com.jfeng.framework.mvc.annotation.Controller;
import com.jfeng.framework.security.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 授权相关请求
 */
@RecordRunningTime
@Controller("/api/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Action.Get("/login")
    public Object login() {
        if (SecurityManager.isLogin()) {
            return "之前已登陆";
        }

        SecurityManager.login(1);
        return "登录成功";
    }

    @Action.Get("/logout")
    public Object logout() {
        if (!SecurityManager.isLogin()) {
            return "未登陆";
        }

        int userId = SecurityManager.logout();
        return userId + "已退出";
    }

    @Action.Get("/info")
    public Object info() {
        if (!SecurityManager.isLogin()) {
            return "未登陆";
        }

        Map<String, Object> infoMap = new HashMap<>();
        int userId = SecurityManager.getUserId();
        infoMap.put("userId", userId);
        return infoMap;
    }
}
