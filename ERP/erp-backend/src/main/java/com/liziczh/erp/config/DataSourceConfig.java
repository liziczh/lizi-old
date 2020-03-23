package com.liziczh.erp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "primaryDS")
    @Qualifier("primaryDS")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource dataSourceOne() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDS")
    @Qualifier("secondaryDS")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
