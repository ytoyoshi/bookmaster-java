package com.example.userService.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.userService.dto.UserRegistrationDto;
import com.example.userService.entity.User;
import com.example.userService.exception.UserAlreadyExistsException;
import com.example.userService.repository.UserRepository;
import com.example.userService.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public UserResponse createUser(UserRegistrationDto dto) {
		if(userRepository.existsByEmail(dto.getEmail())) {
			throw new UserAlreadyExistsException("このメールアドレスは既に登録されています");
		}
		
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setName(dto.getName());
		
		User saveUser = userRepository.save(user);
		
		return convertToResponse(saveUser);
	}
	
	private UserResponse convertToResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setEmail(user.getEmail());
		userResponse.setName(user.getName());
		
		return userResponse;
	}
}