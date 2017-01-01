package com.prapps.app.blog.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prapps.app.blog.dto.BlogPost;
import com.prapps.app.blog.service.BlogService;

@Controller
@ControllerAdvice
@CrossOrigin
@RequestMapping("/blog/page")
public class PageController {

	private BlogService blogService;

	@Inject
	public PageController(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value="/{blogId}", method = {RequestMethod.GET})
	public ModelAndView getPage(@PathVariable("blogId") Long blogId) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		BlogPost blog = blogService.getBlog(blogId);
		Collection<BlogPost> blogs = blogService.findAll();
		modelMap.put("blog", blog);
		modelMap.put("recentBlogs", blogs);
		return new ModelAndView("page", modelMap);
	}
	
	@RequestMapping(value="/{blogId}/{blogCode}", method = {RequestMethod.GET})
	public ModelAndView getPage(@PathVariable("blogId") Long blogId, @PathVariable("blogCode") String blogCode) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		BlogPost blog = blogService.getBlog(blogId);
		Collection<BlogPost> blogs = blogService.findAll();
		modelMap.put("blog", blog);
		modelMap.put("recentBlogs", blogs);
		return new ModelAndView("page", modelMap);
	}
}
