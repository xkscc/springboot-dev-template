package com.xkc.config;

import com.xkc.filter.CustomShiroFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  14:24
 * @Description: shiro配置类
 * @Version: 1.0
 */
@Configuration
public class MyShiroConfig {

    @Bean("shiroFilterFactoryBean")
    @Primary
    public ShiroFilterFactoryBean getShiroFilterFactoryBean
            (@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        /**
         *  正常走登陆认证流程则不需要一下步骤
         */

        // 创建该集合放入自定义shiro过滤器 达到不主动进行登陆认证 即可完成认证
        /*Map<String, Filter> filterMap = new HashMap<>(1);
        filterMap.put("cas", new CustomShiroFilter());
        shiroFilterFactoryBean.setFilters(filterMap);*/


        // 配置过滤规则
        /*Map<String, String> urlFilter = new HashMap<>(2);
        urlFilter.put("/**", "cas");
        urlFilter.put("/actuator/*", "anon");*/

//        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlFilter);

        return shiroFilterFactoryBean;

    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("customRealm") CustomRealm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    @Bean(name = "customRealm")
    public CustomRealm customRealm(){
        CustomRealm userRealm = new CustomRealm();
        return userRealm;
    }

    // 配置角色权限注解
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

}
