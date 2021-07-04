package com.springboot.springbootdemo1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springboot.springbootdemo1.controller.UserController;
import com.springboot.springbootdemo1.entity.User;
import com.springboot.springbootdemo1.entity.UserHa;
import com.springboot.springbootdemo1.mapper.UserMapper;
import com.springboot.springbootdemo1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class Springbootdemo1ApplicationTests {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    UserController userController;

    @Test
    @Rollback
    void contextLoads() {

//        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.SECOND, 600);
//        String token = JWT.create()
//                //.withHeader(map)
//                //设置Payload
//                .withClaim("userId", 10)
//                .withClaim("username", "BLU")
//                //设置token过期时间(60s)
//                .withExpiresAt(instance.getTime())
//                //设置签名
//                .sign(Algorithm.HMAC256("admin"));
//        System.out.println(token);

        List<String> name = userMapper.userPath();
        System.out.println(name);


    }
//    @Test
//    void testJwt(){
//
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("admin")).build();
//        DecodedJWT verify = verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjM3NjU3NjUsInVzZXJJZCI6MTAsInVzZXJuYW1lIjoiQkxVIn0.XyB_t6p-cfnllmVkfHkWBzTqQvd0DNy0_IGv032d36U");
//        System.out.println(verify.getClaim("userId").asInt());
//        System.out.println(verify.getClaims().get("username").asString());
//        System.out.println("过期时间："+verify.getExpiresAt());
//
//    }


}









































































