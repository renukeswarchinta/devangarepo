package com.devangam.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devangam.entity.AuthorityName;
import com.devangam.entity.Role;
import com.devangam.entity.User;
import com.devangam.repository.RoleRepository;
import com.devangam.repository.UserRepository;

@Service
@Transactional
public class RegistrationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository userRolesRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void createUser(User userRequestDTO){
		try {
			User user = userRequestDTO;
		    user.setActive(true);
		    if(user.getRoles() != null){
		    	Role role = userRolesRepository.findOne(Integer.valueOf((AuthorityName.ROLE_USER.getRole())));
		    	user.getRoles().add(role);
		    }
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();//TODO : repalcae with logger and throw the exception
		}
	}
}
