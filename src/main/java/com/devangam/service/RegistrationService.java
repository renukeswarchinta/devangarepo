package com.devangam.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.CommunityLeadersDTO;
import com.devangam.dto.UserRequestDTO;
import com.devangam.dto.UserResponseDTO;
import com.devangam.entity.AuthorityName;
import com.devangam.entity.CommunityLeader;
import com.devangam.entity.Role;
import com.devangam.entity.User;
import com.devangam.repository.CommunityLeaderRepository;
import com.devangam.repository.RoleRepository;
import com.devangam.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.devangam.constants.DevangamConstants.FAILURE;
import static com.devangam.constants.DevangamConstants.SUCCESS;

@Service
@Transactional
public class RegistrationService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository userRolesRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired 
	private CommunityLeaderRepository communityLeaderRepo;

	public CommonResponseDTO createUser(UserRequestDTO userRequestDto) {
		CommonResponseDTO userResponseDto = new CommonResponseDTO();
		// TODO : Pre validation check and Required filed validation is required
		User repositoryUser = null;
		String message = null;
		String status = null;
		try {
			repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
			if (null == repositoryUser) {
				User user = objectMapper.convertValue(userRequestDto, User.class);
				if (null != user) {
					user.setActive(Boolean.TRUE);
					user.setCreatedDate(new Date());
					user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
					if (user.getRoles() != null) {
						Role role = userRolesRepository.findOne(Integer.valueOf((AuthorityName.ROLE_USER.getRole())));
						user.getRoles().add(role);
					}
					userRepository.save(user);
					status = SUCCESS;
					message = "Successfully registered";
					if (logger.isInfoEnabled()) {
						logger.info("Successfully created user. EmailId=" + userRequestDto.getEmail());
					}
				}
			} else {
				status = FAILURE;
				message = "Already registred";
			}
		} catch (Exception exception) {
			status = FAILURE;
			message = "Currently Service is not available";
			if (logger.isErrorEnabled()) {
				logger.error("Create user failed. EmailId=" + userRequestDto.getEmail(), exception);
			}
		}
		userResponseDto.setStatus(status);
		userResponseDto.setMessage(message);
		return userResponseDto;
	}

	// From UI when user wants to register for Matrimony then we need to create
	// all details like matrimony, location etc...after that we set
	// all other objects from user object
	public CommonResponseDTO createUserMatrimony(UserRequestDTO userRequestDto) {
		CommonResponseDTO commonResponseDto = new CommonResponseDTO();;
		String message = null;
		String status = null;
		try {
			if (userRequestDto.isMatrimonyUser()) {
				User repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
				User user = objectMapper.convertValue(userRequestDto, User.class);
				repositoryUser.setMatrimony(user.getMatrimony());
				repositoryUser.setMatrimonyUser(true);
				repositoryUser.setLocation(user.getLocation());
				repositoryUser.setPersonalDetail(user.getPersonalDetail());
				repositoryUser.setProfessionalDetail(user.getProfessionalDetail());
				repositoryUser.setReligionDetail(user.getReligionDetail());
				repositoryUser.setPremiumUser(user.getPremiumUser());
				userRepository.save(repositoryUser);
				status = SUCCESS;
				message = "Successfully registered";
				if (logger.isInfoEnabled()) {
					logger.info("Successfully created MatrimonyUser. EmailId=" + userRequestDto.getEmail());
				}
			}
		} catch (Exception exception) {
			status = FAILURE;
			message = "Currently Service is not available";
			logger.error("Matrimony user creation failed. EmailId=" + userRequestDto.getEmail(),exception);
		}
		commonResponseDto.setStatus(status);
		commonResponseDto.setMessage(message);
		return commonResponseDto;

	}

	// @ExceptionHandler(DevangamException.class)
	public UserResponseDTO getUserDetails(String emailId) {
		UserResponseDTO userResponsetDto = new UserResponseDTO();
		String message = null;
		String status = null;
		try {
			User repositoryUser = userRepository.findByUsername(emailId);
			if (null != repositoryUser) {
				UserRequestDTO userResquestDto = objectMapper.convertValue(repositoryUser, UserRequestDTO.class);
				userResponsetDto.setUserRequestDto(userResquestDto);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Retriving user details successfully by email. mailId=" + userResponsetDto);
			}
			status = SUCCESS;
		} catch (Exception exception) {
			status = FAILURE;
			message = "Retriving user details failed";
			logger.error("Retriving user details failed" , exception);
		}
		userResponsetDto.setStatus(status);
		userResponsetDto.setMessage(message);
		return userResponsetDto;

	}
	
	public CommonResponseDTO saveCommunityLeaders(CommunityLeadersDTO communityLeadersDTO){
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
			
			CommunityLeader communityLeader = objectMapper.convertValue(communityLeadersDTO, CommunityLeader.class);
			communityLeaderRepo.save(communityLeader);
			commonResponseDTO.setStatus(SUCCESS);
			commonResponseDTO.setMessage("Saved Successfully");
		}catch(Exception exception){
			commonResponseDTO.setMessage("Error while saving community leaders data");
			commonResponseDTO.setStatus(FAILURE);
		}
		 return  commonResponseDTO;
		
	}
}
