package com.funstuff.routine.resource;


import com.funstuff.routine.payload.request.LoginFrom;
import com.funstuff.routine.payload.response.Jwt;
import com.funstuff.routine.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/login",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> login(@RequestBody  LoginFrom loginFrom){

        System.out.println(loginFrom.getEmail() + " , " + loginFrom.getPassword());

        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginFrom.getEmail(),loginFrom.getPassword()));
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token =  jwtTokenUtil.generateAccessToken(authentication);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,token).body(new Jwt(token));

    }
}
