package com.prapps.app.core.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationManager implements AuthenticationManager {

	private DaoAuthenticationProvider daoAuthenticationProvider;
	
	@Autowired 
	public RestAuthenticationManager(DaoAuthenticationProvider daoAuthenticationProvider) {
		this.daoAuthenticationProvider = daoAuthenticationProvider;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return daoAuthenticationProvider.authenticate(authentication);
	}

}
