package com.prapps.app.core.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.core.dto.BlogPost;
import com.prapps.app.core.exception.BlogServiceException;
import com.prapps.app.core.service.BlogService;

@Controller
@ControllerAdvice
@CrossOrigin
@RequestMapping("/rest/blog")
public class BlogController {

	private BlogService blogService;

	@Inject
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value="/**", method = {RequestMethod.OPTIONS})
	@Consumes(value = MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseEntity<HttpStatus> options(@RequestBody BlogPost blogPost) {
		HttpHeaders headers = new HttpHeaders();
		Set<HttpMethod> allow = new HashSet<HttpMethod>();
		allow.add(HttpMethod.GET);
		allow.add(HttpMethod.POST);
		allow.add(HttpMethod.PUT);
		allow.add(HttpMethod.DELETE);
		allow.add(HttpMethod.OPTIONS);
		headers.setAllow(allow);

		return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	@Consumes(value = MediaType.APPLICATION_JSON)
	public @ResponseBody BlogPost create(@RequestBody BlogPost blogPost) {
		return blogService.create(blogPost);
	}
	
	@RequestMapping(value="/{id}", method = {RequestMethod.PUT, RequestMethod.OPTIONS})
	@Consumes(value = MediaType.APPLICATION_JSON)
	public @ResponseBody BlogPost update(@RequestBody BlogPost blogPost) {
		return blogService.update(blogPost);
	}

	@ExceptionHandler(BlogServiceException.class)
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<BlogPost> getBlogs() {
		return blogService.findAll();
	}

	@RequestMapping(value = "/{id}/{blogCode}", method = RequestMethod.GET)
	@Produces(value = MediaType.APPLICATION_JSON)
	public @ResponseBody BlogPost getBlog(@PathVariable("id") Long id, @PathVariable(value = "blogCode") String blogCode) {
		return blogService.getBlog(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Produces(value = MediaType.APPLICATION_JSON)
	public @ResponseBody BlogPost getBlog(@PathVariable("id") Long id) {
		return getBlog(id, null);
	}
	
}
