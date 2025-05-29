package com.mrad.UniReport.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mrad.UniReport.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    var token = this.recoveryToken(request);

	    if (token != null) {
	        var email = tokenService.validateToken(token);

	        if (email != null && !email.isEmpty()) { 
	            UserDetails user = userRepository.findByEmail(email);

	            if (user != null) {
	                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            } 
	        } 
	    } 
	    filterChain.doFilter(request, response);
	}
	
	private String recoveryToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader == null) {
			return null;
		}
		else {
			return authHeader.replace( "Bearer ", "");
		}
	}
}
