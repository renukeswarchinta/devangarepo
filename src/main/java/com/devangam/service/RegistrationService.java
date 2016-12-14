package com.devangam.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devangam.dto.MatrimonyDTO;
import com.devangam.dto.UserRequestDTO;
import com.devangam.entity.AuthorityName;
import com.devangam.entity.Matrimony;
import com.devangam.entity.Role;
import com.devangam.entity.User;
import com.devangam.repository.MatrimonyRepository;
import com.devangam.repository.RoleRepository;
import com.devangam.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class RegistrationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository userRolesRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private MatrimonyRepository matrimonyRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	public void createUser(UserRequestDTO userRequestDTO){
		User user = objectMapper.convertValue(userRequestDTO, User.class);
		try {
		    if (null != user) {
				user.setActive(true);
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				if (user.getRoles() != null) {
					Role role = userRolesRepository.findOne(Integer.valueOf((AuthorityName.ROLE_USER.getRole())));
					user.getRoles().add(role);
				}
				userRepository.save(user);
			}
		} catch (Exception e) {
			e.printStackTrace();//TODO : repalcae with logger and throw the exception
		}
	}
	// From UI when user wants to register for Matrimony then we need to create all details like matrimony, location etc...after that we set
	// all other objects from user object
	public void createUserMatrimony(UserRequestDTO userRequestDto){
		if(userRequestDto.isMatrimonyUser()){
			User repositoryUser = userRepository.findByUsername(userRequestDto.getUsername());
			User user = objectMapper.convertValue(userRequestDto, User.class);
			
			repositoryUser.setMatrimony(user.getMatrimony());
			repositoryUser.setMatrimonyUser(true);
			repositoryUser.setLocation(user.getLocation());
			repositoryUser.setPersonalDetail(user.getPersonalDetail());
			repositoryUser.setProfessionalDetail(user.getProfessionalDetail());
			repositoryUser.setReligionDetail(user.getReligionDetail());
			repositoryUser.setPremiumUser(user.getPremiumUser());
			userRepository.save(repositoryUser);
		}
		
	}
	//@ExceptionHandler(DevangamException.class)
	public UserRequestDTO getUserDetails(String emailId) {
		UserRequestDTO userRequestDto = new UserRequestDTO();
		try {
			User repositoryUser = userRepository.findByUsername(emailId);
			if(null != repositoryUser){
				userRequestDto = objectMapper.convertValue(repositoryUser, UserRequestDTO.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRequestDto;
		
	}
}
