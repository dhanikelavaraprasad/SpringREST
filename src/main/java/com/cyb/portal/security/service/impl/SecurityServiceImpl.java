package com.cyb.portal.security.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cyb.portal.security.service.SecurityService;

public class SecurityServiceImpl implements SecurityService {

	@Override
	public Boolean hasProtectedAccess() {
		return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")));
	}

}
