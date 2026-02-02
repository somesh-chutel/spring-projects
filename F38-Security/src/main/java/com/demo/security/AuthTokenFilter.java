package com.demo.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private UtilsJwt utliJwt;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try{
            System.out.println("AUTH TOKEN CALLED");
        String jwt = getJwtToken(request);
        if(jwt != null && utliJwt.validateJwt(jwt)){
            String userName = utliJwt.getUserNamefromToken(jwt);
            Claims claims = utliJwt.getAllClaims(jwt);
            List<String> roles = claims.get("roles", List.class);
            List<GrantedAuthority> authorities = roles.stream().map(role -> (GrantedAuthority)new SimpleGrantedAuthority(role)).toList();
//            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,null , authorities);
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        }catch (Exception e){
            System.out.println("Token is null");
        }
        filterChain.doFilter(request,response);
		
	}
	
	private String getJwtToken(HttpServletRequest request){
        String jwt = utliJwt.getJwtFromHeader(request);
        return jwt;
    }

}
