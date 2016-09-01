package com.prapps.app.chat.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.chat.dto.Message;
import com.prapps.app.chat.dto.MessageResponse;

@Controller
@RequestMapping("/chat")
public class ChatController {

	private static final int MSG_BUF_SIZE = 1024;
	private static final int MSG_MAX_LEN = 1024;
	private Message[] messages = new Message[MSG_BUF_SIZE];
	private int currentIndex = -1;
	
	@RequestMapping(path = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody MessageResponse addMessage(@RequestParam("msg") String msg, @RequestParam("lastIndex") int lastIndex) {
		if (msg == null) {
			return new MessageResponse();
		}
		
		if (msg.length() >= MSG_MAX_LEN) {
			msg = msg.substring(0, MSG_MAX_LEN);	
		}
		messages[++currentIndex % MSG_BUF_SIZE] = new Message(currentIndex, msg);
		return new MessageResponse(currentIndex, Arrays.asList(Arrays.copyOfRange(messages, lastIndex+1, currentIndex+1)));
	}
	
	@RequestMapping(path = "/get", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody MessageResponse addMessage(@RequestParam("lastIndex") int lastIndex) {
		return new MessageResponse(currentIndex, Arrays.asList(Arrays.copyOfRange(messages, lastIndex+1, currentIndex+1)));
	}
}
