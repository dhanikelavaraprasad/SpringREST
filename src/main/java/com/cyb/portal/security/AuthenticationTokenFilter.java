package com.cyb.portal.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.cyb.portal.util.URIConstants;

public class AuthenticationTokenFilter extends
		UsernamePasswordAuthenticationFilter {

	/*
	 * @Value("${portal.token.header}") private String tokenHeader;
	 */

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(URIConstants.TOKEN_HEADER);
		String username = this.tokenUtils.getUsernameFromToken(authToken);


		if (username != null
				&& SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDetails = this.userDetailsService
						.loadUserByUsername(username);
				if (this.tokenUtils.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication
							.setDetails(new WebAuthenticationDetailsSource()
									.buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(
							authentication);
				}
				chain.doFilter(request, response);
			} catch (AuthenticationException authenticationException) {
				SecurityContextHolder.clearContext();
				// authenticationEntryPoint.commence(request, response,
				// authenticationException);
			}
		}

	}

}
