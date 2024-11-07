package com.example.helloService.service;

import org.springframework.stereotype.Service;

import com.example.helloService.model.Message;
import com.example.helloService.repository.MessageRepository;

@Service
public class HelloService {

	private final MessageRepository messageRepository;

	public HelloService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public String getHelloMessage(String languageCode) {
		return messageRepository.findFirstByLanguageCodeOrderByIdDesc(languageCode)
				.map(Message::getContent)
				.orElse("Hello (Default Message)");
	}

	// すべての言語のメッセージを取得
	public record MessageResponse(String en, String ja, String es) {
	}

	public MessageResponse getAllMessages() {
		String enMessage = getHelloMessage("en");
		String jaMessage = getHelloMessage("ja");
		String esMessage = getHelloMessage("es");

		return new MessageResponse(enMessage, jaMessage, esMessage);
	}
}