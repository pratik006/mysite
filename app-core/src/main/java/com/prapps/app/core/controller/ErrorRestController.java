package com.prapps.app.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/error")
public class ErrorRestController {
	@Autowired Environment env;
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(Throwable.class)
	@RequestMapping("/{code}")
    public String exception(@PathVariable String code) throws Exception {
        return code;
	}
}
