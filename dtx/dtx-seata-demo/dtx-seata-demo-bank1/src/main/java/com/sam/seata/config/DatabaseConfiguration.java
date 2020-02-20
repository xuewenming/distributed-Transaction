package com.sam.seata.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Mr.xuewenming
 * @title: DatabaseConfiguration
 * @projectName distributed-Transaction
 * @description: TODO
 * @date 2020/2/1822:47
 */
@Configuration
public class DatabaseConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ds0")
    public DruidDataSource ds0() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }


    @Bean
    @Primary
    public DataSource dataSource(DruidDataSource ds0) {
        DataSourceProxy dataSourceProxy = new DataSourceProxy(ds0);
        return dataSourceProxy;
    }

}
