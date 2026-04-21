package org.example.tliaswebmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class jjwtTest {

    @Test
    public void testGenerateJwt() {
        Map<String, Object> map = new HashMap<>();
        map.put("heima", 666);
        map.put("Anon", "555");

        String jwt = Jwts.builder()     //创建令牌
                .signWith(SignatureAlgorithm.HS256, "5Y2D5pep54ix6Z+z")  //设置签名算法和密钥
                .addClaims(map)     //添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))  //设置令牌有效期(deadline)
                .compact();     //生成令牌

        System.out.println(jwt);

    }

    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJBbm9uIjoiNTU1IiwiaGVpbWEiOjY2NiwiZXhwIjoxNzcwNjQ5MTg5fQ.tE6y_fyO8Dbq0XKqim7-z0aC5badEeubeU8di5lumOw";

        Claims body = Jwts.parser()     //解析令牌
                .setSigningKey("5Y2D5pep54ix6Z+z")   //设置密钥
                .parseClaimsJws(token)  //输入要解析的令牌
                .getBody();     //获取解析结果

        System.out.println(body);
    }
}
