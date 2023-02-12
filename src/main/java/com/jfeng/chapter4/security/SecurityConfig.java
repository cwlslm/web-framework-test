package com.jfeng.chapter4.security;

import com.jfeng.framework.mvc.annotation.Configuration;
import com.jfeng.framework.security.AuthzType;
import com.jfeng.framework.security.annotation.SecurityAuthzValidator;
import com.jfeng.framework.security.annotation.SecurityRules;
import com.jfeng.framework.security.bean.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置Security的规则
 */
@Configuration
public class SecurityConfig {

    /**
     * 配置Security规则
     */
    @SecurityRules
    public List<Rule> setRules() {
        List<Rule> ruleList = new ArrayList<>();
        // 任意访问
        ruleList.add(new Rule("/api/auth/login", AuthzType.ANON));
        // 需要登陆
        ruleList.add(new Rule("/api/auth/logout", AuthzType.AUTHC));
        ruleList.add(new Rule("/hello.*", AuthzType.AUTHC));
        // 需要权限
        ruleList.add(new Rule(".*", AuthzType.PERMS));
        return ruleList;
    }

    /**
     * 定义Security验证器
     */
    @SecurityAuthzValidator
    public boolean validator(String requiredPermission) {
        if (requiredPermission.equals("客户:列表")) {
            return true;
        }
        else if (requiredPermission.equals("客户:详情")) {
            return true;
        }

        return false;
    }
}
