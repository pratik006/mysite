package com.prapps.app.core.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.prapps.app.core.dto.User;
import com.prapps.app.core.dto.UserDetailsImpl;

@Component
public class PrincipalHelper {
	private SoftReference<Map<String, User>> userMap;
	private UserDetailsService service;
	
	@Autowired
	public PrincipalHelper(UserDetailsService service) {
		userMap = new SoftReference<Map<String,User>>(new HashMap<String,User>());
		this.service = service;
	}
	
	public boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() > 0 
				&& !SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority().equalsIgnoreCase("ROLE_ANONYMOUS");
	}
	
	public User getUserDetails() {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		User user = null;
		
		if (userMap == null || userMap.get() == null) {
			userMap = new SoftReference<Map<String,User>>(new HashMap<String,User>());
		}
		if ((user = userMap.get().get(username)) == null) {
			user  = ((UserDetailsImpl) service.loadUserByUsername(username)).getUser();
			userMap.get().put(username, user);
		}
		
		return user;
	}
}