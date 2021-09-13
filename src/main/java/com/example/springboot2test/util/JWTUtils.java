package com.example.springboot2test.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils implements Serializable {

    private static final String SING="!Q@W3e4r%T^Y"; //签名

    //生成 token  header.payload.sing
    public static String getToken(Map<String,String> map){
        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.MINUTE,5);//默认5分钟后过期

        //创建JWT builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v) -> {
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));

        return token;
    }

    //验证token合法性  后返回token信息
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}
