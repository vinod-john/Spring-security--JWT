package com.john.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.john.service.CustomeUserdetailService;
import com.john.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtservice; 
	
	@Autowired
	private ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			String header=request.getHeader("authorization");
			String token=null;
			String username=null;
			if(header !=null && header.startsWith("Bearer")) {
				token=header.substring(7);
				username=jwtservice.extractUserName(token);
			}
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails=context.getBean(CustomeUserdetailService.class).loadUserByUsername(username);
				if(jwtservice.validateToken(token,userDetails)) {
					UsernamePasswordAuthenticationToken authtoken=
							new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
					authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authtoken);
				}
			}
			filterChain.doFilter(request, response);
		
	}

}
