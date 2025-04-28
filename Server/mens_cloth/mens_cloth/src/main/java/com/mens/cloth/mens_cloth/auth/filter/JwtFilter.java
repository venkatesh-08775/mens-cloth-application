package com.mens.cloth.mens_cloth.auth.filter;

import com.mens.cloth.mens_cloth.auth.service.JwtService;
import com.mens.cloth.mens_cloth.user.userService.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    protected JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       final String reqHeader = request.getHeader("Authorization");

       if( reqHeader == null || !reqHeader.startsWith("Bearer ")){
           filterChain.doFilter(request,response);
           return;
       }

       String token = reqHeader.substring(7);

       String email = jwtService.extractUserName(token);

       if(email == null){
           filterChain.doFilter(request,response);
           return;
       }

       final UserDetails userDetails = userService.findByEmail(email);
       if(!jwtService.validateToken(token,userDetails)){
           filterChain.doFilter(request,response);
           return;
       }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

    }
}
