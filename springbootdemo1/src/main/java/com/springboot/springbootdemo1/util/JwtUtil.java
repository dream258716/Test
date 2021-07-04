package com.springboot.springbootdemo1.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class JwtUtil {

    //自定义私钥
    final static String base64EncodedSecretKey = "你的私钥";
    //设置token过期时间  10分钟
    final static int TOKEN_EXP = 1000 * 60 * 10;

    public static String getToken(){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, TOKEN_EXP);
        String token = JWT.create()
                //.withHeader(map)
                //设置Payload
                .withClaim("userId", 10)
                .withClaim("username", "BLU")
                //设置token过期时间(60s)
                .withExpiresAt(instance.getTime())
                //设置签名
                .sign(Algorithm.HMAC256(base64EncodedSecretKey));
            System.out.println(token);

            return token;

    }

    public static Claims checkToken(String token){

        final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();

        return claims;
    }



}
