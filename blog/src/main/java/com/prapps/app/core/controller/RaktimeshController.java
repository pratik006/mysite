package com.prapps.app.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
@CrossOrigin
public class RaktimeshController {

	@RequestMapping(value = "/raktimeshphotography")
	public ModelAndView page() {
		return new ModelAndView("raktimesh");
	}
	
}
