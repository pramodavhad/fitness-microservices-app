package com.fitness.userservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	public UserResponse register(RegisterRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			User existingUser = userRepository.findByEmail(request.getEmail());
			
			UserResponse userResponse = new UserResponse();
			userResponse.setId(existingUser.getId());
			userResponse.setKeycloakId(existingUser.getKeycloakId());
			userResponse.setEmail(existingUser.getEmail());
			userResponse.setPassword(existingUser.getPassword());
			userResponse.setFirstName(existingUser.getFirstName());
			userResponse.setLastName(existingUser.getLastName());
			userResponse.setCreatedAt(existingUser.getCreatedAt());
			userResponse.setUpdatedAt(existingUser.getUpdatedAt());
			return userResponse;
		}
		
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setKeycloakId(request.getKeycloakId());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		
		User savedUser = userRepository.save(user);
		
		UserResponse userResponse = new UserResponse();
		userResponse.setId(savedUser.getId());
		userResponse.setKeycloakId(savedUser.getKeycloakId());
		userResponse.setEmail(savedUser.getEmail());
		userResponse.setPassword(savedUser.getPassword());
		userResponse.setFirstName(savedUser.getFirstName());
		userResponse.setLastName(savedUser.getLastName());
		userResponse.setCreatedAt(savedUser.getCreatedAt());
		userResponse.setUpdatedAt(savedUser.getUpdatedAt());
		return userResponse;
	}

	public UserResponse getUserProfile(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new RuntimeException("User not Found"));
		
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setEmail(user.getEmail());
		userResponse.setPassword(user.getPassword());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setCreatedAt(user.getCreatedAt());
		userResponse.setUpdatedAt(user.getUpdatedAt());
		return userResponse;
	}

	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		return users.stream().map(user -> {
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setKeycloakId(user.getKeycloakId());
			userResponse.setEmail(user.getEmail());
			userResponse.setPassword(user.getPassword());
			userResponse.setFirstName(user.getFirstName());
			userResponse.setLastName(user.getLastName());
			userResponse.setCreatedAt(user.getCreatedAt());
			userResponse.setUpdatedAt(user.getUpdatedAt());
			return userResponse;
		}).toList();
	}

	public boolean validateUser(String userId) {
		log.info("Calling User Validation API for userId: {}", userId);
		return userRepository.existsByKeycloakId(userId);
	}

}
