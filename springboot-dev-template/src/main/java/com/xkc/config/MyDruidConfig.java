package com.xkc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  14:28
 * @Description: 数据库连接池配置类 方便配置多数据源
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
@Data
public class MyDruidConfig {

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private Boolean testWhileIdle;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    private Boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private String connectionProperties;
}
