package com.chatBot.indraChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatBot.indraChat.service.AiBotService;

@RestController
public class AiBotController {

	@Autowired
	private AiBotService service;

	@PostMapping("/chat")
	public String getResponse(@RequestBody String text) {

		return service.callApi(text);

	}

}
