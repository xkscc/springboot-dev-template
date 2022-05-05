package com.xkc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("进入权限认证........");

        // 通过PrincipalCollection获取到认证时传入的用户信息  根据认证时传入的数据 可进行转型拿到数据
        Object sessoinUser = principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        log.info("认证的用户信息如下: " + sessoinUser);

        // 拿到用户信息 进行查询获取到用户的角色以及权限 放入返回对象中 从而实现权限的认证

        // 将角色以及权限信息放入simpleAuthorizationInfo对象 此处暂为null
        simpleAuthorizationInfo.setRoles(null);
        simpleAuthorizationInfo.setStringPermissions(null);

        log.info("获取的角色信息如下: " + simpleAuthorizationInfo.getRoles());
        log.info("获取的角色权限如下: " + simpleAuthorizationInfo.getStringPermissions());

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("进入认证方法......");

        // 通过AuthenticationToken拿到认证时传入的用户名称
        // 拿到用户名称进行数据库查询拿到用户信息

        /**
         *  将用户信息放入返回对象中 实现认证
         *  第一个参数可以是对象 可以是一个字符串
         *  该处传入的可以在授权时通过principalCollection.getPrimaryPrincipal()获取该处传入的参数  需要进行转型
         *  可将数据库查询到的对象放入
         *
         *  第二个参数为密码 通过查询到用户对象 获取密码放入即可
         *
         *  第三个参数可为空字符串"" 也可调用getName方法放入值
         */
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("username","123",getName());

        return authenticationInfo;
    }

}
