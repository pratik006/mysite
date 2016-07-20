package com.prapps.app.chat.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.chat.dto.Message;
import com.prapps.app.chat.dto.MessageResponse;
import com.prapps.app.chat.dto.MessageThread;
import com.prapps.app.chat.service.ChatService;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.util.PrincipalHelper;
import com.prapps.app.core.util.time.TimeUtil;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired PrincipalHelper helper;
	@Autowired ChatService chatService;
	
	@RequestMapping(path = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody MessageResponse addMessage(@RequestParam("msg") String msg, @RequestParam("threadId") Long threadId, @RequestParam("lastIndex") int lastIndex) {
		User user = helper.getUserDetails();
		if (!"chat".equals(user.getAppCode())) {
			return new MessageResponse();
		}
		if (msg == null) {
			return new MessageResponse();
		}
		
		if (msg.length() >= 150) {
			msg = msg.substring(0, 150);	
		}
		chatService.addHttpMessage(threadId, msg, user);
		return getMessages(threadId, lastIndex);
	}
	
	@RequestMapping(path = "/get", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody MessageResponse getMessages(@RequestParam("threadId") Long threadId, @RequestParam("lastIndex") int lastIndex) {
		User user = helper.getUserDetails();
		return chatService.getMessages(threadId, lastIndex, user);
	}
}
