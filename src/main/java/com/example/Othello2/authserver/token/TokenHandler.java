package com.example.Othello2.authserver.token;

import com.example.Othello2.authserver.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenHandler {
    private static final String SECRET_KEY = "yourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKey";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(UserEntity userEntity) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .claim("user_id", userEntity.getId())
                .claim("username", userEntity.getUsername())
                .setSubject(userEntity.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static Long getUserIdFromToken(String token) {
        token = token.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("user_id", Long.class);
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
