package com.hkta.educentresystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AuditData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 247955556487832285L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private boolean active;

	@Column(name = "role")
	private String role;
	
	@JoinColumn(name = "tutorial_centre_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private TutorialCenter tutorialCentreId;

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

	public TutorialCenter getTutorialCentreId() {
		return tutorialCentreId;
	}

	public void setTutorialCentreId(TutorialCenter tutorialCentreId) {
		this.tutorialCentreId = tutorialCentreId;
	}	
	
	
}
