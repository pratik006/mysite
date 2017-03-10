package com.prapps.app.core.controller;

import java.net.URL;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
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
@EnableAsync
@RequestMapping("/rest")
public class CoreController {

	private UserService userService;
	private static boolean keepAlive = false;
	
	@Inject
	public CoreController(UserService userService) {
		this.userService = userService;
	}
	
	@Async
	private void start(String url) {
		if (!keepAlive) {
			keepAlive = true;
			while(keepAlive) {
				try {
					new URL(url).openConnection();
					Thread.sleep(1000*60*60*12);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	@RequestMapping(value = "/keepAlive", method = RequestMethod.GET)
	@Consumes(value=  MediaType.APPLICATION_JSON)
    public @ResponseBody String keepAlive(HttpServletRequest request) {
		start(request.getRequestURL().toString());
		return "alive";
    }
	
	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	@Consumes(value=  MediaType.APPLICATION_JSON)
    public @ResponseBody User getUserInfo() {
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.findUser(userDetails.getUsername());
    }
}
