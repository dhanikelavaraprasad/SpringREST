package com.cyb.portal.dao;

import com.cyb.portal.entity.TmsUsers;

public interface UserDAO {

	public TmsUsers findByUsername(String username);
}
