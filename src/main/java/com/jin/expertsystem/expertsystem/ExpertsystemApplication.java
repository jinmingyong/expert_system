package com.jin.expertsystem.expertsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan(basePackages = {"com.jin.expertsystem.expertsystem.business.**.dao"})
public class ExpertsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpertsystemApplication.class, args);
    }


}
