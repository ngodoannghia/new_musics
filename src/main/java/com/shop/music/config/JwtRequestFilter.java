package com.shop.music.config;


import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shop.music.common.JwtTokenUtil;
import com.shop.music.service.JwtUserDetailsService;
import com.shop.music.service.SessionTokenService;
import com.shop.music.service.UserService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SessionTokenService sesionToken;

    @Autowired
    UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("token");
        String username = null;
        String jwtToken = null;
        Boolean isJwt = true;

        if (requestTokenHeader != null) {
            jwtToken = requestTokenHeader;

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                isJwt = true;
            } catch (Exception e) {
                System.out.println("JWT Token invalid");
                isJwt = false;
            }
        } else {

        }
        if (isJwt) {
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.jwtUserDetailsService.loadUser(this.jwtTokenUtil.getUser(jwtToken).getUser_id());
                if (userService.findByUsername(userDetails.getUsername()) == null){

                } else {
                    if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        		usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        } else {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SessionTokenService.ObjectToken objToken = sesionToken.validate(jwtToken);
                if (objToken != null) {
                    UserDetails userDetails = this.jwtUserDetailsService.loadUser(objToken.uuid);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                } else {

			}
            }
        }
        chain.doFilter(request, response);
    }
}