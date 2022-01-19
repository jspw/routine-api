package com.funstuff.routine.filter;

import com.funstuff.routine.repository.UserRepository;
import com.funstuff.routine.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.List.of;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private  JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(request.getRequestURI());

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        System.out.println(header);

        if(header.isEmpty()){
            filterChain.doFilter(request,response);
            return;
        }

        if( !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        final String token = header.split(" ")[1].trim();

        if(!jwtTokenUtil.isValidate(token)){
            filterChain.doFilter(request,response);
            return;
        }

        UserDetails userDetails = (UserDetails) userRepository.findUserById(jwtTokenUtil.getUserId(token));

        System.out.println(userDetails.toString());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails
                ,null,ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(of()));


        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
