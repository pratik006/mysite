package com.prapps.app.chat.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prapps.app.chat.dto.Message;
import com.prapps.app.core.util.PrincipalHelper;
import com.prapps.app.core.util.time.TimeUtil;

@RestController
@RequestMapping("/rest/secured/chess")
public class ChessController {
	public static final int MSG_BUF = 10;
	@Autowired TimeUtil timeUtil;
	@Autowired PrincipalHelper principalHelper;

	List<Message> messages = new ArrayList<Message>(MSG_BUF);
	int index = 0;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Message> getLatestMessages() {
		List<Message> copiedList = new ArrayList<Message>(messages);
		Collections.reverse(copiedList);
		return copiedList;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addMessage(@RequestBody Message message) {
		message.setId(index);
		message.setUserName(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		message.setTime(timeUtil.getCurrentTime());
		int targetIndex = index++ % MSG_BUF;
		if (messages.size() > targetIndex && messages.get(targetIndex) != null) {
			messages.remove(targetIndex);
		}
		messages.add(targetIndex, message);
	}
}
