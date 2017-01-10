package com.devangam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.entity.User;
import com.devangam.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findUserEntity(String email) {
			return  userRepository.findByUsername(email);
	}

	public void updateUserEntity(User persistObj) {
		userRepository.save(persistObj);
	}
	
	
}
