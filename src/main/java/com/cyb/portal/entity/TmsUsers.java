package com.cyb.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cyb.portal.entity.base.BaseEntity;

@Entity
@Table(name = "tms_users")
public class TmsUsers extends BaseEntity{
	
	
	private static final long serialVersionUID = 3118855342051551440L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
    private long id;
	
    @Column(name="USER_NAME")
    private String username;
    
    @Column(name="PASSWORD")
    private String password;
    
    @Column(name="STATUS")
    private String status;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="USER_ROLE")
    private String authorities;

    
    
	public TmsUsers() {
		super();
	}

	public TmsUsers(long id, String username, String password, String status, String email, String authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
		this.email = email;
		this.authorities = authorities;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
    

}
