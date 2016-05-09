package com.cyb.portal.util;

public class URIConstants {
	
	public static final String DUMMY_EMP = "api/emp/dummy";
	public static final String GET_EMP = "api/emp/{id}";
	public static final String GET_ALL_EMP = "api/emp/list";
	public static final String CREATE_EMP = "api/emp/create";
	public static final String DELETE_EMP = "api/emp/delete/{id}";
	public static final String EDIT_EMP = "api/emp/edit/{id}";

	public static final String TOKEN_HEADER = "X-Auth-Token";
	public static final String TOKEN_SECRET = "eHTRcUpaSWfGgTIJsC4rM6lPMm8es87pYPrELJUwoNqMXN7zWIzGQGq1hgdPCM5u";
	public static final Long TOKEN_EXPIRE = (long) 604800;
	public static final String ROUTE_AUTH = "api/auth";
	public static final String ROUTE_AUTH_REFRESH = "api/refresh";
	
	
}
