package org.hbsp.cl.report.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@EnableEncryptableProperties
@EncryptablePropertySource(name = "encrypted", value = {"classpath:app.properties"})
@PropertySource(value = "classpath:jasypt.properties")
public class TestConfig {
    @Bean
    public HikariConfig hikariConfig(
            @Value("${datasource.driver}") String dsDriver,
            @Value("${datasource.url}") String dsUrl,
            @Value("${datasource.username}") String dsUsername,
            @Value("${datasource.password}") String dsPassword) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dsDriver);
        hikariConfig.setJdbcUrl(dsUrl);
        hikariConfig.setUsername(dsUsername);
        hikariConfig.setPassword(dsPassword);

        hikariConfig.setMaximumPoolSize(20);
        return hikariConfig;
    }

    @Bean
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
