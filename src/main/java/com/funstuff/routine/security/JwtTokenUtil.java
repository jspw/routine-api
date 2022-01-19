package com.funstuff.routine.security;


import com.funstuff.routine.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final String secret ="sdadasdasdasdasdas";
    private final String issuer = "mh shifat";

    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000 ))
                .signWith(SignatureAlgorithm.ES256, secret)
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
