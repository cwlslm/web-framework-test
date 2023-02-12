package com.jfeng.chapter4.controller;

import com.jfeng.chapter4.aspect.recordrunningtime.RecordRunningTime;
import com.jfeng.framework.mvc.annotation.Action;
import com.jfeng.framework.mvc.annotation.Controller;
import com.jfeng.framework.mvc.annotation.PathVariable;
import com.jfeng.framework.mvc.bean.ResultView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RecordRunningTime
@Controller
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    /**
     * 测试
     */
    @Action.Get("/hello")
    public String hello() {
        return "Hello World";
    }

    @Action.Get("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }

    /**
     * 首页自动跳转
     */
    @Action.Get("/")
    public ResultView index() {
        return new ResultView("/customer");
    }
}
