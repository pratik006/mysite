package com.prapps.app.core.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.core.api.UserService;
import com.prapps.app.core.dto.User;

@Controller
@ControllerAdvice
@RequestMapping("/rest")
public class CoreController {

	private UserService userService;
	
	@Inject
	public CoreController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	@Consumes(value=  MediaType.APPLICATION_JSON)
    public @ResponseBody User getUserInfo() {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.findUser(userDetails.getUsername());
    }
}
