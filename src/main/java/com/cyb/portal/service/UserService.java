package com.cyb.portal.service;

import com.cyb.portal.entity.TmsUsers;

public interface UserService {

	public TmsUsers findByUsername(String username);
}
