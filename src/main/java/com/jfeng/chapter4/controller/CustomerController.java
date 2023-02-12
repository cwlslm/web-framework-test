package com.jfeng.chapter4.controller;

import com.jfeng.chapter4.aspect.recordrunningtime.RecordRunningTime;
import com.jfeng.chapter4.model.Customer;
import com.jfeng.chapter4.service.CustomerService;
import com.jfeng.framework.mvc.annotation.Action;
import com.jfeng.framework.mvc.annotation.Controller;
import com.jfeng.framework.ioc.annotation.Inject;
import com.jfeng.framework.mvc.bean.RequestParam;
import com.jfeng.framework.mvc.bean.ResultView;
import com.jfeng.framework.security.annotation.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 处理客户管理相关请求
 */
@RecordRunningTime
@Controller
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Inject
    private CustomerService customerService;

    /**
     * 进入 客户列表 界面
     */
    @Action.Get("/customer")
    @Permission("客户:列表")
    public ResultView customer() {
        List<Customer> customerList = customerService.getCustomerList();
        return new ResultView("customer.jsp").addData("customerList", customerList);
    }

    /**
     * 获取客户列表数据
     */
    @Action.Get("/customer_api")
    @Permission("客户:列表")
    public List<Customer> customer_api() {
        return customerService.getCustomerList();
    }

    /**
     * 进入 客户详情 界面
     */
    @Action.Get("/customer_show")
    @Permission("客户:详情")
    public ResultView customer_show(long id) {
        if (id == 0) {
            return null;
        }

        Customer customer = customerService.getCustomer(id);
        return new ResultView("customer_show.jsp").addData("customer", customer);
    }

    /**
     * 进入 编辑客户 界面
     */
    @Action.Get("/customer_edit")
    @Permission("客户:编辑")
    public ResultView customer_edit(long id) {
        if (id == 0) {
            return null;
        }

        Customer customer = customerService.getCustomer(id);
        return new ResultView("customer_edit.jsp").addData("customer", customer);
    }

    /**
     * 更新客户
     */
    @Action.Post("/customer_update")
    @Permission("客户:修改")
    public ResultView customer_update(long id, RequestParam param) {
        if (id == 0) {
            return null;
        }

        customerService.updateCustomer(id, param.getFieldMap(), param.getFile("photo"));
        return new ResultView("/customer");
    }

    /**
     * 进入 新增客户 界面
     */
    @Action.Get("/customer_create")
    @Permission("客户:新增")
    public ResultView customer_create() {
        return new ResultView("customer_create.jsp");
    }

    /**
     * 新增客户
     */
    @Action.Post("/customer_create_api")
    @Permission("客户:新增")
    public ResultView customer_create_api(RequestParam param) {
        customerService.createCustomer(param.getFieldMap(), param.getFile("photo"));
        return new ResultView("/customer");
    }

    /**
     * 删除客户
     */
    @Action.Get("/customer_delete")
    @Permission("客户:删除")
    public ResultView customer_delete(long id) {
        if (id == 0) {
            return null;
        }

        customerService.deleteCustomer(id);
        return new ResultView("/customer");
    }
}
