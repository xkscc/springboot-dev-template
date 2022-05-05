package com.xkc.filter;

import com.xkc.utils.SpringBeanUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  15:50
 * @Description: 该过滤器主要实现 不走登陆认证 从而实现权限角色认证
 * @Version: 1.0
 */
@Slf4j
public class CustomShiroFilter extends BasicHttpAuthenticationFilter {

    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (!getSubject(request, response).isAuthenticated()) {
            executeLogin(request, response);
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("登录失败");
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        log.info("执行登录");

        // 通过spring对象拿到用户对象 Object换为用户实体类即可 前提spring上下文中存在该对象
        Object sessionUser = SpringBeanUtil.getBean(Object.class);

        // shiro登录认证账号密码统一使用userId
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();

        // 放入用户的账号以及密码 也可随便给值  根据具体情况来
        usernamePasswordToken.setPassword("123".toCharArray());
        usernamePasswordToken.setUsername("zhangsan");
        getSubject(request, response).login(usernamePasswordToken);
        return true;
    }
}
