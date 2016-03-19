package com.prapps.app.core.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.prapps.app.core.dto.User;

@Component
public class PrincipalHelper {
	private SoftReference<Map<String, User>> userMap;
	
	public PrincipalHelper() {
		userMap = new SoftReference<Map<String,User>>(new HashMap<String,User>());
	}
	
	public User getUserDetails() {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		User user = null;
		
		if (userMap == null) {
			userMap = new SoftReference<Map<String,User>>(new HashMap<String,User>());
		}
		if ((user = userMap.get().get(username)) == null) {
			
		}
		
		return user;
	}
}