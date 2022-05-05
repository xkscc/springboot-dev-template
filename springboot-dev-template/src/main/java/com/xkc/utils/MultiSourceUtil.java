package com.xkc.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.xkc.config.MyDruidConfig;

import java.sql.SQLException;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  15:29
 * @Description: 多数据源配置工具
 * @Version: 1.0
 */
public class MultiSourceUtil {

    public static void setDataSource(DruidDataSource dataSource, MyDruidConfig druidConfig, String url, String username,
                                     String password, String driverClassName) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        //具体配置
        dataSource.setInitialSize(druidConfig.getInitialSize());
        dataSource.setMinIdle(druidConfig.getMinIdle());
        dataSource.setMaxActive(druidConfig.getMaxActive());
        dataSource.setMaxWait(druidConfig.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidConfig.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidConfig.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidConfig.getValidationQuery());
        dataSource.setTestWhileIdle(druidConfig.getTestWhileIdle());
        dataSource.setTestOnBorrow(druidConfig.getTestOnBorrow());
        dataSource.setTestOnReturn(druidConfig.getTestOnReturn());
        dataSource.setPoolPreparedStatements(druidConfig.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidConfig.getMaxPoolPreparedStatementPerConnectionSize());

        /**
         * 这个是用来配置 druid 监控sql语句的 非常有用 如果你有两个数据源 这个配置哪个数据源就监控哪个数据源的sql 同时配置那就都监控
         */
        try {
            dataSource.setFilters(druidConfig.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setConnectionProperties(druidConfig.getConnectionProperties());
    }
}
