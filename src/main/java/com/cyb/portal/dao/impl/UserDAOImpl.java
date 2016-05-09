package com.cyb.portal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.portal.dao.UserDAO;
import com.cyb.portal.entity.TmsUsers;
import com.cyb.portal.util.HibernateUtil;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final String USER_NAME = "username";
	
	@Autowired
    private HibernateUtil hibernateUtil;

	@Override
	public TmsUsers findByUsername(String username) {
		return hibernateUtil.findByUsername(USER_NAME, username, TmsUsers.class);
	}

}
