package com.joeladjidan.gestiondestock.config;

import com.joeladjidan.gestiondestock.services.auth.ApplicationUserDetailsService;
import com.joeladjidan.gestiondestock.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
public class ApplicationRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private ApplicationUserDetailsService userDetailsService;

  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

		    final String authHeader = request.getHeader("Authorization");
		    
		    String userEmail = null;
		    String jwt = null;
		    String idEntreprise = null;
		
		    if(authHeader != null && authHeader.startsWith("Bearer ")) {
		      jwt = authHeader.substring(7);
		      try {
		    	    userEmail = jwtUtil.extractUsername(jwt);
		    	} catch (ExpiredJwtException e) {
		    	    log.error("Token is expired");
		    	    try {
						  String isRefreshToken = request.getHeader("refreshToken");
						  String requestURL = request.getRequestURL().toString();
						// allow for Refresh Token creation if following conditions are true.
						if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
							allowForRefreshToken(e, request);
						} else
							request.setAttribute("exception", e);
					} catch (BadCredentialsException ex) {
						request.setAttribute("exception", ex);
					} catch (Exception ex) {
						System.out.println(ex);
					}
		    	} catch (SignatureException e) {
				   log.info("Error signature ", e);
		    	} catch(Exception e){
			      System.out.println(" Some other exception in JWT parsing ");
		    	}
		      idEntreprise = jwtUtil.extractIdEntreprise(jwt);
		    }
		
		    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
		      if (jwtUtil.validateToken(jwt, userDetails)) {
		        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
		            userDetails, null, userDetails.getAuthorities()
		        );
		        usernamePasswordAuthenticationToken.setDetails(
		            new WebAuthenticationDetailsSource().buildDetails(request)
		        );
		        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		      }
		    }
		    MDC.put("idEntreprise", idEntreprise);
		    chain.doFilter(request, response);
  }

  private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		// Set the claims so that in controller we will be using it to create
		// new JWT
		request.setAttribute("claims", ex.getClaims());

	}
}
