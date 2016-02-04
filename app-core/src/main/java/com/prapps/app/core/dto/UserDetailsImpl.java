package com.prapps.app.core.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return !user.isCredentialExpired();
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return !user.isLocked();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return !user.isExpired();
	}
	
	@Override
	public String getUsername() {
		return user.getUserName();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		for(Role role : user.getRoles()) {
			list.add(new SimpleGrantedAuthority(role.getName()));
		}
		return list;
	}
}
