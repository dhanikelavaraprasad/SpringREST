package com.cyb.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.portal.security.TokenUtils;
import com.cyb.portal.security.json.AuthenticationRequest;
import com.cyb.portal.security.json.AuthenticationResponse;
import com.cyb.portal.security.model.PortalUsers;
import com.cyb.portal.util.URIConstants;



@RestController
@RequestMapping(URIConstants.ROUTE_AUTH)
public class AuthenticationController {
	
	  private final Logger logger = Logger.getLogger(this.getClass());

	 /* @Value("${portal.token.header}")
	  private String tokenHeader;*/

	  @Autowired
	  private AuthenticationManager authenticationManager;

	  @Autowired
	  private TokenUtils tokenUtils;

	  @Autowired
	  private UserDetailsService userDetailsService;

	  @RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {

	    // Perform the authentication
	   /* Authentication authentication = this.authenticationManager.authenticate(
	      new UsernamePasswordAuthenticationToken(
	        authenticationRequest.getUsername(),
	        authenticationRequest.getPassword()
	      )
	    );
	    SecurityContextHolder.getContext().setAuthentication(authentication);*/

	    // Reload password post-authentication so we can generate token
	    UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	  //  User user = userDao.findByUsernameAndPassword("username", "password");
	    SecurityContext ctx = SecurityContextHolder.createEmptyContext();
	    SecurityContextHolder.setContext(ctx);
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
	    authentication.setDetails(userDetails);
	    ctx.setAuthentication(authentication);
	    String token = this.tokenUtils.generateToken(userDetails);

	    // Return the token
	    return ResponseEntity.ok(new AuthenticationResponse(token));
	  }

	  @RequestMapping(value = URIConstants.ROUTE_AUTH_REFRESH, method = RequestMethod.GET)
	  public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
	    String token = request.getHeader(URIConstants.TOKEN_HEADER);
	    String username = this.tokenUtils.getUsernameFromToken(token);
	    PortalUsers user = (PortalUsers) this.userDetailsService.loadUserByUsername(username);
	    //if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
	      String refreshedToken = this.tokenUtils.refreshToken(token);
	      return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
	   // } else {
	   //   return ResponseEntity.badRequest().body(null);
	   // }
	  }
}
