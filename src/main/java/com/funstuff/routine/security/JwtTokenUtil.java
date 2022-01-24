package com.funstuff.routine.security;


import com.funstuff.routine.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private  String secret = "routine" ;
    private  String issuer = "shifat";
    private  int jwtExpirationMs = 24*60*60*1000;

    public String generateAccessToken(Authentication authentication){
        UserDetailsImpl userPrinciple = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(String.valueOf(userPrinciple.getId()))
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs ))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public long getUserId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject().split(",")[0]);
    }

    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return  claims.getSubject().split(",")[1];
    }

    public Date getExpireDate(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return  claims.getExpiration();
    }

    public boolean isValidate(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception err){
            err.printStackTrace();
        }
        return false;
    }


}
