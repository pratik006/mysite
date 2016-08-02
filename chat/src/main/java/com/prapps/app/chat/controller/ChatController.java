package com.prapps.app.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.chat.dto.MessageRequest;
import com.prapps.app.chat.dto.MessageResponse;
import com.prapps.app.chat.service.ChatService;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.util.PrincipalHelper;

@Controller
@RequestMapping("rest//chat")
public class ChatController {
	
	@Autowired PrincipalHelper helper;
	@Autowired ChatService chatService;
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
	public @ResponseBody MessageResponse addMessage(@RequestBody MessageRequest request) {
		//User user = helper.getUserDetails();
		User user = new User();
		user.setUserId(1L);
		user.setAppCode("chat");
		user.setUserName("barsha");
		if (!"chat".equals(user.getAppCode())) {
			return new MessageResponse();
		}
		if (request.getMessage() == null) {
			return new MessageResponse();
		}
		
		String msg = request.getMessage();
		if (msg.length() >= 150) {
			msg = msg.substring(0, 150);	
		}
		int curIndex = chatService.addHttpMessage(request.getThreadId(), msg, user);
		MessageResponse response = new MessageResponse();
		response.setCurIndex(curIndex);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody MessageResponse getMessages(@RequestParam("threadId") Long threadId, @RequestParam("lastIndex") int lastIndex) {
		//User user = helper.getUserDetails();
		User user = new User();
		user.setUserId(1L);
		user.setAppCode("chat");
		user.setUserName("barsha");
		return chatService.getMessages(threadId, lastIndex, user);
	}
}
