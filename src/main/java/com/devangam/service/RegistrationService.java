package com.devangam.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devangam.dto.UserRequestDTO;
import com.devangam.entity.AuthorityName;
import com.devangam.entity.Role;
import com.devangam.entity.User;
import com.devangam.security.repository.RoleRepository;
import com.devangam.security.repository.UserRepository;

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
		    user.setEmail("test@gmail.com");
		    user.setPassword(bCryptPasswordEncoder.encode("test123"));
		    user.setFirstname("vajpai");
		    user.setLastname("bingi");
		    user.setEmail("test@gmail.com");
		    user.setIsActive(true);
		    Role authority = new Role();
		    authority.setRoleName(AuthorityName.ROLE_USER.getRoleName());
		    Set<Role> authorities = new HashSet<Role>();
		    authorities.add(authority);
		    user.setRoles(authorities);
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
