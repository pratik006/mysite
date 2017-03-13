package com.prapps.app.core.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest")
public class LoginController {
	@RequestMapping(value="/loginSuccess", method = {RequestMethod.POST})
	public @ResponseBody String login() {
		return "success";
	}
	
	@RequestMapping(value="/secured/test", method = {RequestMethod.GET})
	public @ResponseBody String test() {
		return "secured resource";
	}
}
