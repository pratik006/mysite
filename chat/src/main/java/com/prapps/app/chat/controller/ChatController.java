package com.prapps.app.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prapps.app.chat.dto.Message;
import com.prapps.app.chat.dto.MessageResponse;
import com.prapps.app.chat.service.ChatService;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.util.PrincipalHelper;

@RestController
@RequestMapping(value = "/rest/secured/chat")
public class ChatController {
	
	@Autowired PrincipalHelper helper;
	@Autowired ChatService chatService;
	
	@RequestMapping(value = "/{threadId}", method = {RequestMethod.PUT, RequestMethod.POST})
	public MessageResponse addMessage(@PathVariable Long threadId, @RequestBody Message request) {
		User user = helper.getUserDetails();
		if (request.getMsg() == null) {
			return new MessageResponse();
		}
		
		String msg = request.getMsg();
		if (msg.length() >= 150) {
			msg = msg.substring(0, 150);	
		}
		int curIndex = chatService.addHttpMessage(threadId, msg, user);
		MessageResponse response = new MessageResponse();
		response.setCurIndex(curIndex);
		return response;
	}
	
	@RequestMapping(value = "/{threadId}", method = RequestMethod.GET)
	public MessageResponse getMessages(@PathVariable Long threadId, @RequestParam("lastIndex") int lastIndex) {
		User user = helper.getUserDetails();
		user.setUserId(1L);
		user.setAppCode("chat");
		/*if (!"1".equals(user.getAppCode())) {
			return new MessageResponse();
		}*/
		return chatService.getMessages(threadId, lastIndex, user);
	}
}
