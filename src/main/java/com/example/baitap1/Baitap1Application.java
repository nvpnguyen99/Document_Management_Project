package com.example.baitap1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class Baitap1Application {

    @Bean
    JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.setQueryTimeout(20); //20 seconds
        jdbcTemplate.setFetchSize(10);  //fetch 10 rows at a time
        return jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(Baitap1Application.class, args);
    }

}
