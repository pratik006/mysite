package com.prapps.app.core.security.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.dto.UserDetailsImpl;
import com.prapps.app.core.security.handler.RestAuthenticationFailureHandler;
import com.prapps.app.core.security.jwt.RestAuthenticationManager;

@Component
public class RestAuthFilter extends AbstractAuthenticationProcessingFilter {

	private ObjectMapper mapper;

	@Autowired
	public RestAuthFilter(RestAuthenticationManager restAuthenticationManager,
			RestAuthenticationSuccessHandler restAuthenticationSuccessHandler,
			RestAuthenticationFailureHandler restAuthenticationFailuerHandler,
			ObjectMapper mapper) {
		super("/rest/login");
		this.setAuthenticationManager(restAuthenticationManager);
		this.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
		this.setAuthenticationFailureHandler(restAuthenticationFailuerHandler);
		this.mapper = mapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationServiceException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("405" + request.getMethod());
        }

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
          BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
          while ((line = reader.readLine()) != null)
            jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        User user = null;
        try {
			user = mapper.readValue(jb.toString().getBytes(), User.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


        if(user.getUserName() == null) {
        	throw new AuthenticationServiceException("401");
        }

        UserDetails ud = new UserDetailsImpl(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(ud.getUsername(), ud.getPassword(), null);
        return getAuthenticationManager().authenticate(authentication);
    }

}
