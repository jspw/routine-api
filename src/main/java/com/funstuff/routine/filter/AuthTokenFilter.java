package com.funstuff.routine.filter;

import com.funstuff.routine.Exception.CustomException;
import com.funstuff.routine.security.JwtTokenUtil;
import com.funstuff.routine.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

   @ Autowired
   private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt =parseJwt(request);
            System.out.println(jwt);
            if(jwt!=null &&   jwtTokenUtil.isValidate(jwt)){
                String username = jwtTokenUtil.getEmail(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
//            else {
//                throw new CustomException("Invalid token, Please login.",HttpStatus.BAD_REQUEST);
//            }
        }catch  (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request,response);

    }


    private  String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7,headerAuth.length());
        }
//        else throw new CustomException("No bearer token found.", HttpStatus.BAD_REQUEST);
        return null;
    }
}
