package com.authenticate.app.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authenticate.app.util.Jwtutil;

@Component
public class JwtRequestFilter  extends OncePerRequestFilter{

	@Autowired
	private Jwtutil jwtutil;
	@Autowired
	private UserDetailsService userdetailservice;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		if(request.getRequestURI().contains("/getjwt"))
		{
			filterChain.doFilter(request, response);
		}
		//System.out.println(" token")
		else if(SecurityContextHolder.getContext().getAuthentication() == null)
		{
			final String getjwt =request.getHeader("Authorization"); //header contain "bearer header" 
			
			final String username = jwtutil.getUsernameFromToken(getjwt);
			UserDetails userdetail = this.userdetailservice.loadUserByUsername(username);
			
			if(jwtutil.validateToken(getjwt, userdetail))
			{
				UsernamePasswordAuthenticationToken usernamepasswordauthenticationtoken =
						new UsernamePasswordAuthenticationToken(
								userdetail, null, userdetail.getAuthorities()
								);
				usernamepasswordauthenticationtoken.setDetails(
						new WebAuthenticationDetailsSource()
						.buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamepasswordauthenticationtoken);
				
			}
			filterChain.doFilter(request, response);
		}
		
		
		
	}catch(Exception e)
		{
		TokenValidationFailedException.message="Token Validation Failed";
		throw new TokenValidationFailedException();
		}
	}
	
	

}
