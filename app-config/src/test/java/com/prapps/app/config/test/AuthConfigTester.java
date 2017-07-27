package com.prapps.app.config.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.security.jwt.JwtTokenHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthConfigTester {
	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldGetToken() throws Exception {
		User user = new User();
		user.setUserName("pratik006@gmail.com");
		user.setPassword("password123");
		mvc.perform(MockMvcRequestBuilders.post("/rest/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			//.andExpect( content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					String token = result.getResponse().getHeader("x-authtoken");
					String contentType = result.getResponse().getHeader("Content-Type");
					String content = new String(result.getResponse().getContentAsByteArray());
					
					System.out.println("contentType: "+contentType+"\tcontent: " + content);
					System.out.println("token: " + token);
  					Assert.assertEquals("pratik006@gmail.com", JwtTokenHelper.verifyToken(token).getUserName());
				}
			});
	}

	@Test
	public void shouldFailWithUnauthException() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/rest/secured/abc")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}

	@Test
	public void shouldAccessSecuredResource() throws Exception {
		GrantedAuthority auth = new GrantedAuthority() {
			@Override
			public String getAuthority() { return "ROLE_ADMIN"; }
		};
		String token = JwtTokenHelper.createJsonWebToken(new UsernamePasswordAuthenticationToken("pratik006@gmail.com", "password123", Arrays.asList(auth)));
		MvcResult result =  mvc.perform(MockMvcRequestBuilders.get("/rest/secured/test")
				.header("Authorization", "Bearer " + token).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		//System.out.println("content"+result.getResponse().);
	}
}
