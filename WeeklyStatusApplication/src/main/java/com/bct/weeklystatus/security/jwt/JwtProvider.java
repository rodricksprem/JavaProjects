package com.bct.weeklystatus.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.bct.weeklystatus.services.UserPrinciple;

import java.util.Date;
 //reference -- https://grokonez.com/spring-framework/spring-boot/angular-spring-boot-jwt-authentication-example-angular-6-spring-security-mysql-full-stack-part-2-build-backend

@Component
public class JwtProvider {
 
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
 
    @Value("${weeklystatus.app.jwtSecret}")
    private String jwtSecret;
 
    @Value("${weeklystatus.app.jwtExpiration}")
    private int jwtExpiration;
 
    public String generateJwtToken(Authentication authentication) {
 
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        System.out.println("generateJwtToken "+userPrincipal.getUsername());
        return Jwts.builder()
                    .setSubject((userPrincipal.getUsername()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpiration*10000))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                      .setSigningKey(jwtSecret)
                      .parseClaimsJws(token)
                      .getBody().getSubject();
    }
}