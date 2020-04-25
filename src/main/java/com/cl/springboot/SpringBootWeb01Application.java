package com.cl.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cl.springboot.mapper")
public class SpringBootWeb01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWeb01Application.class, args);
    }

}
