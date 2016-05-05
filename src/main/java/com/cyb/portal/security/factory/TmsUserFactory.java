package com.cyb.portal.security.factory;

import org.springframework.security.core.authority.AuthorityUtils;

import com.cyb.portal.entity.TmsUsers;
import com.cyb.portal.security.model.PortalUsers;

public class TmsUserFactory {
	
	public static PortalUsers create(TmsUsers user) {
	    return new PortalUsers(
	      user.getId(),
	      user.getUsername(),
	      user.getPassword(),
	      user.getEmail(),
	      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
	    );
	  }

}
