package com.YinnnH.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JwtUtils {
    // 保持硬编码的密钥，注意这种方式存在安全风险
    private static final String SIGN_KEY = "YinnnH";


    private static final Logger LOGGER = Logger.getLogger(JwtUtils.class.getName());

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return 生成的JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        if (claims == null || claims.isEmpty()) {
            throw new IllegalArgumentException("载荷内容不能为空");
        }
        try {
            return Jwts.builder()
                    .addClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                    .setExpiration(new Date(System.currentTimeMillis() + 1200*1000))//20分钟
                    .compact();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "生成JWT时发生异常: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        if (jwt == null || jwt.isEmpty()) {
            throw new IllegalArgumentException("JWT令牌不能为空");
        }
        try {
            return Jwts.parser()
                    .setSigningKey(SIGN_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "解析JWT时发生异常: " + e.getMessage(), e);
            return null;
        }
    }
}