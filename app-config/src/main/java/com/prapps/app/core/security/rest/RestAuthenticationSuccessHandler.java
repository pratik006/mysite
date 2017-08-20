package com.prapps.app.core.security.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.prapps.app.core.api.UserService;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.security.jwt.JwtTokenHelper;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	@Qualifier("mappingJackson2HttpMessageConverter")
	MappingJackson2HttpMessageConverter messageConverter;
	@Autowired UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		final String authToken = JwtTokenHelper.createJsonWebToken(authentication);
		response.addHeader("x-authtoken", authToken);
		response.setContentType("application/json");

		String username = (String) authentication.getPrincipal();
		User user = userService.findUser(username);
		user.setPassword(null);
		
		PrintWriter writer = response.getWriter();
        messageConverter.getObjectMapper().writeValue(writer, user);
        writer.flush();
	}
}
