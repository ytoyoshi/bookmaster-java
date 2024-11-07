package com.example.techBookNaviFront.client;

import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.techBookNaviFront.dto.UserRegistrationDto;
import com.example.techBookNaviFront.form.UserRegisterForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceClient {

	private final RestTemplate restTemplate;

	@Value("${service.user.base-url}")
	private String baseUrl;

	@Value("${service.user.endpoints.register}")
	private String registerEndpoint;

	public void registerUser(UserRegisterForm form) throws ServerException {
		String url = UriComponentsBuilder
				.fromHttpUrl(baseUrl)
				.path(registerEndpoint)
				.build()
				.toUriString();
		
		UserRegistrationDto dto = convertToRequest(form);
		
		try {
			restTemplate.postForEntity(url, dto, Void.class);
		}catch (HttpClientErrorException e) {
			log.error("User registration failed", e);
			throw new ServerException("ユーザー登録に失敗しました", e);
		}
	}

	private UserRegistrationDto convertToRequest(UserRegisterForm form) {
		UserRegistrationDto dto = new UserRegistrationDto();
		dto.setEmail(form.getEmail());
		dto.setPassword(form.getPassword());
		dto.setName(form.getName());
		return dto;
	}
}
