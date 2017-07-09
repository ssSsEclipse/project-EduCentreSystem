package com.hkta.educentresystem.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 247955556487832285L;

	private Long id;

	private String username;

	private String password;

	private boolean active;

	private String role;

	private Long tutorialCentreId;
	
	private Date createDateTime;

	private Date modifiedDateTime;

	public Long getId() {
		return id;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getTutorialCentreId() {
		return tutorialCentreId;
	}

	public void setTutorialCentreId(Long tutorialCentreId) {
		this.tutorialCentreId = tutorialCentreId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}
	
	
}
