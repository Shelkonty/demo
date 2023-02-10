package com.example.security;

import com.example.entity.User;
import com.example.services.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
      try {
          String jwt = getJwtFromRequest(httpServletRequest);
          if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
              Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
              User userDetails = customUserDetailsService.loadUserById(userId);

              UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                      userDetails, null, Collections.emptyList()
              );
              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
              SecurityContextHolder.getContext().setAuthentication(authentication);
          }
      }catch (Exception e){

          LOG.error("could not ser server authentication");

      }

      filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
    private String getJwtFromRequest(HttpServletRequest request){
        String bearToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if(StringUtils.hasText(bearToken) && bearToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
            return bearToken.split("")[1];

        }
        return null;
    }

}
