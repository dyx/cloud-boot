package com.cloud.boot.auth.util;

import cn.hutool.core.lang.UUID;
import com.cloud.boot.auth.service.UserDetailsVo;
import com.cloud.boot.common.core.exception.BizException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author lhd
 */
@Component
public class JwtUtil {

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("ff4037bce70d4e34b1a3ce51bcfccc49856a4ce9d9d6452eb11b2eec78b42b32".getBytes());;
    private static final long EXPIRATION_TIME = 24*3600*1000;

    public String generateToken(UserDetailsVo userDetailsVo) {
        DefaultClaims claims = new DefaultClaims(new HashMap<>());
        claims.put("id", userDetailsVo.getId());
        claims.put("username", userDetailsVo.getUsername());
        return createToken(claims, userDetailsVo.getUsername());
    }

    public Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        return extractClaim(token, claims -> claims.get("id", Long.class));
    }

    public String getUsername(String token) {
        return extractClaim(token, claims -> claims.get("username", String.class));
    }


    public Date getExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(currentTimeMillis))
                .expiration(new Date(currentTimeMillis + EXPIRATION_TIME))
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build();
            return parser.parseSignedClaims(token).getPayload();
        } catch (Exception ignored) {}
        return new DefaultClaims(new HashMap<>());
    }

    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}