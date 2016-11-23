package com.devangam.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devangam.dto.UserRequestDTO;
import com.devangam.model.security.Authority;
import com.devangam.model.security.AuthorityName;
import com.devangam.model.security.User;
import com.devangam.security.repository.UserRepository;


import com.devangam.security.repository.RoleRepository;

@Service
@Transactional
public class RegistrationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository userRolesRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void createUser(UserRequestDTO userRequestDTO){
		try {
			User user = new User();
		    user.setUsername("test@gmail.com");
		    user.setPassword(bCryptPasswordEncoder.encode("test123"));
		    user.setFirstname("vajpai");
		    user.setLastname("bingi");
		    user.setEmail("test@gmail.com");
		    user.setEnabled(Boolean.TRUE);
		    user.setLastPasswordResetDate(new Date());
		    Authority authority = new Authority();
		    authority.setName(AuthorityName.ROLE_USER);
		    List<Authority> authorities = new ArrayList<Authority>();
		    authorities.add(authority);
		    user.setAuthorities(authorities);
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
