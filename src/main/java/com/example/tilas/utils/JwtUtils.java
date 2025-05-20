package com.example.tilas.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static Long expire = 300000L;
    private static String key = "learnspringbootprojectthesecureisitheimatilas";
    public static String getJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key) // 设置签名算法和密钥
                .setClaims(claims) // 设置自定义的内容
                .setExpiration(new Date(System.currentTimeMillis() + expire)) //设置有效期
                .compact();
        return jwt;
    }

    public static Claims parseJwt(String jwt) {
//        try {
//            Claims body = Jwts.parser()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(jwt)
//                    .getBody();
//            System.out.println(body);
//            return true; // 如果没报错，就表示令牌合法
//        } catch (Exception e) {
//            return false; // 如果报错了，令牌就是非法的
//        }
        Claims body = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(body);
        return body;
    }
}
