package com.xkc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.xkc.utils.MultiSourceUtil;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author: chenwei
 * @CreateTime: 2022-05-05  14:32
 * @Description: 当前使用的数据连接配置
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.one")
@Data
@MapperScan(basePackages = OneDataResourceConfig.PACKAGE, sqlSessionFactoryRef = "oneSqlSessionFactory")
public class OneDataResourceConfig {

    public static final String PACKAGE = "com.xkc.mapper.dataone";
    public static final String MAPPER_LOCATION  = "classpath:mapper/one/*.xml";

    private String driverClassName;
    private String username;
    private String password;
    private String url;

    @Resource
    private MyDruidConfig myDruidConfig;

    @Primary
    @Bean("oneDataSource")
    @ConditionalOnExpression("${spring.datasource.druid.enable:true}")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        MultiSourceUtil.setDataSource(dataSource, myDruidConfig, url, username, password, driverClassName);
        return dataSource;
    }

    @Primary
    @Bean("oneDataSource")
    @ConditionalOnMissingBean(name = "oneDataSource")
    public DataSource dataSourceLocal() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean(name = "oneTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("oneDataSource") DataSource masterDataSource) {
        return new DataSourceTransactionManager(masterDataSource);
    }

    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("oneDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(OneDataResourceConfig.MAPPER_LOCATION));

        return sessionFactory.getObject();
    }
}
