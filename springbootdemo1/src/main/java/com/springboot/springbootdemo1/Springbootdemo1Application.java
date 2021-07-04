package com.springboot.springbootdemo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.springboot.springbootdemo1.mapper")
public class Springbootdemo1Application {

    public static void main(String[] args) {

        SpringApplication.run(Springbootdemo1Application.class, args);
    }

}
