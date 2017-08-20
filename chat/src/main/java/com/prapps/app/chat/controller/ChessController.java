package com.prapps.app.chat.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prapps.app.chat.dto.Message;
import com.prapps.app.chat.dto.MessageResponse;
import com.prapps.app.core.util.PrincipalHelper;
import com.prapps.app.core.util.time.TimeUtil;

@RestController
@RequestMapping("/rest/secured/chess")
public class ChessController {
	public static final int MSG_BUF = 10;
	public static final int MAX_QUEUE_LEN = 1000;
	@Autowired TimeUtil timeUtil;
	@Autowired PrincipalHelper principalHelper;

	List<Message> messages = new ArrayList<Message>(MSG_BUF);
	int index = 0;
	
	private Queue<String> serverConsole = new PriorityQueue<String>();
	private Queue<String> clientConsole = new PriorityQueue<String>();

	@RequestMapping(method = RequestMethod.GET)
	public MessageResponse getLatestMessages() {
		List<Message> copiedList = new ArrayList<Message>(messages);
		Collections.reverse(copiedList);
		MessageResponse response = new MessageResponse();
		response.setMessages(copiedList);
		return response;
	}
	
	@RequestMapping(value = "/last", method = RequestMethod.GET)
	public Message getLastMessages() {
		if (messages.isEmpty()) {
			return null;
		}
		List<Message> copiedList = new ArrayList<Message>(messages);
		Collections.reverse(copiedList);
		return copiedList.get(0);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addMessage(@RequestBody Message message, HttpServletRequest req) {
		message.setId(index);
		message.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		message.setTime(timeUtil.getCurrentTime());
		message.setIp(req.getRemoteAddr());
		int targetIndex = index++ % MSG_BUF;
		if (messages.size() > targetIndex && messages.get(targetIndex) != null) {
			messages.remove(targetIndex);
		}
		messages.add(targetIndex, message);
	}
	
	@RequestMapping(value="/server", method = RequestMethod.POST)
	public String server(@RequestBody Message msg) {
		if (msg.getMsg() != null && msg.getMsg().length() > 0) {
			if (serverConsole.size() > MAX_QUEUE_LEN) {
				return "overflow";
			}
			
			synchronized (serverConsole) {
				serverConsole.add(msg.getMsg());
			}
		}
		
		if (clientConsole.isEmpty()) {
			return "empty";
		}
		
		StringBuilder sb = new StringBuilder();
		synchronized (clientConsole) {
			while ( !clientConsole.isEmpty() )
				sb.append(clientConsole.poll());
		}
		
		return sb.toString();
	}
	
	@RequestMapping(value="/client", method = RequestMethod.POST)
	public String client(@RequestBody Message msg) {
		if (msg.getMsg() != null && msg.getMsg().length() > 0) {
			if (clientConsole.size() > MAX_QUEUE_LEN) {
				return "overflow";
			}
			
			synchronized (clientConsole) {
				clientConsole.add(msg.getMsg());
			}
		}
		
		if (serverConsole.isEmpty()) {
			return "empty";
		}
		
		StringBuilder sb = new StringBuilder();
		synchronized (serverConsole) {
			while ( !serverConsole.isEmpty() )
				sb.append(serverConsole.remove());
		}
		
		return sb.toString();
	}
}
