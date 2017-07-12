package com.hkta.educentresystem.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomUserDetails extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3237987015547986538L;
	private Long id;

	public CustomUserDetails(Long id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return super.getAuthorities();
	}

	@JsonIgnore
	public String getPassword() {
		return super.getPassword();
	}

	@JsonIgnore
	public String getUsername() {
		return super.getUsername();
	}

	@JsonIgnore
	public boolean isEnabled() {
		return super.isEnabled();
	}

	@JsonIgnore
	public boolean isAccountNonExpired() {
		return super.isAccountNonExpired();
	}

	@JsonIgnore
	public boolean isAccountNonLocked() {
		return super.isAccountNonLocked();
	}

	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return super.isCredentialsNonExpired();
	}
}
