package com.cyb.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cyb.portal.dao.UserDAO;
import com.cyb.portal.entity.TmsUsers;
import com.cyb.portal.security.factory.TmsUserFactory;

public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TmsUsers user = this.userDAO.findByUsername(username);

	    if (user == null) {
	      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
	    } else {
	      return TmsUserFactory.create(user);
	    }
	}

	

}
