package com.zcyi.ordersystem.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    //SING签名的设置
    public static final String SING = "zrercndfd";

    public static String getToken(Map<String, String> map) {
        //设置令牌的过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 15);

        //创建JWT builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SING));
    }

    public static DecodedJWT verify(String token) {
       return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}