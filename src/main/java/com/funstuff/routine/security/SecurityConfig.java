package com.funstuff.routine.security;


import com.funstuff.routine.filter.JwtTokenFilter;
import com.funstuff.routine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  final UserRepository userRepository;

    @Autowired
    private final  JwtTokenFilter jwtTokenFilter;
    
    public SecurityConfig(UserRepository userRepository,JwtTokenFilter jwtTokenFilter){
        super();
        this.userRepository = userRepository;
        this.jwtTokenFilter = jwtTokenFilter;

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(username -> (UserDetails) userRepository.findByUsername(username));
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable()
//                    .sessionManagement().sessionCreationPolicy(STATELESS).and()
//                    .exceptionHandling()
//                    .authenticationEntryPoint(
//                            (request, response, ex) -> {
//                                System.out.println(ex.getMessage());
//                                ex.printStackTrace();
//                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//                            }
//                    )
//                    .and()
//                    .authorizeRequests()
//                    // public endpoints
//                        .antMatchers("/public/login").permitAll()
//                        .antMatchers(HttpMethod.GET,"/todos/**").permitAll()
//                        .antMatchers(HttpMethod.POST,"/users/**").permitAll()
//                // private endpoints
//                        .anyRequest().authenticated().and()
//                    .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
//                            logger.error("Unauthorized request - {}", ex.getMessage());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                        }
                )
                .and();

        // Set permissions on endpoints
        http.authorizeRequests()
                // Swagger endpoints must be publicly accessible
//                .antMatchers("/").permitAll()
//                .antMatchers(format("%s/**", restApiDocPath)).permitAll()
//                .antMatchers(format("%s/**", swaggerPath)).permitAll()
                // Our public endpoints
                .antMatchers("/public/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                // Our private endpoints
                .anyRequest().authenticated();

        // Add JWT token filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public CorsFilter corsFilter(){
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedOrigin("*");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**",configuration);
//        return new CorsFilter();
//
//    }




}
