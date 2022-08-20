package com.huan.demo21.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class druidConfig {
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource getDruid(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

}
