package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAILURE;
import static com.devangam.constants.DevangamConstants.SUCCESS;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devangam.archive.Document;
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
	@Autowired
    private FileSystemDocumentService fileSystemDocumentService;

	public CommonResponseDTO createUser(UserRequestDTO userJsonRequestDto) {
		CommonResponseDTO userResponseDto = new CommonResponseDTO();
		// TODO : Pre validation check and Required filed validation is required
		User repositoryUser = null;
		String message = null;
		String status = null;
		boolean isError = false;
		boolean isSuccess = false;
		UserRequestDTO userRequestDto = null;
		try {
			userRequestDto = objectMapper.readValue(userJsonRequestDto.getUserRequestJson(), UserRequestDTO.class);
			userRequestDto.setMultipartFile(userJsonRequestDto.getMultipartFile());
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
					repositoryUser = userRepository.save(user);
					if(null != userRequestDto.getMultipartFile()){
						fileSystemDocumentService.insert(new Document(userRequestDto.getMultipartFile().getBytes(),String.valueOf(repositoryUser.getUserId()),null,null));
					}
					isSuccess = true;
					message = "Successfully registered";
					if (logger.isInfoEnabled()) {
						logger.info("Successfully created user. EmailId=" + userRequestDto.getEmail());
					}
				}
			} else {
				isError = true;
				message = "Already registred";
			}
		} catch (Exception exception) {
			isError = true;
			message = "create user failed.";
			if (logger.isErrorEnabled()) {
				logger.error("Create user failed. EmailId=" + userRequestDto.getEmail(), exception);
			}
		}
		if (isSuccess) {
			status = SUCCESS;
		} else {
			status = FAILURE;
		}
		userResponseDto.setStatus(status);
		userResponseDto.setMessage(message);
		return userResponseDto;
	}

	// From UI when user wants to register for Matrimony then we need to create
	// all details like matrimony, location etc...after that we set
	// all other objects from user object
	public CommonResponseDTO createUserMatrimony(UserRequestDTO userRequestDto) {
		CommonResponseDTO commonResponseDto = new CommonResponseDTO();
		;
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
			logger.error("Matrimony user creation failed. EmailId=" + userRequestDto.getEmail(), exception);
		}
		commonResponseDto.setStatus(status);
		commonResponseDto.setMessage(message);
		return commonResponseDto;

	}

	// @ExceptionHandler(DevangamException.class)
	public UserResponseDTO getUserDetails(String emailId) {
		boolean isError = false;
		boolean isSuccess = false;
		String message = null;
		UserResponseDTO userResponsetDto = new UserResponseDTO();
		if (!StringUtils.isEmpty(emailId)) {
			try {
				User repositoryUser = userRepository.findByUsername(emailId);
				if (null != repositoryUser) {
					UserRequestDTO userResquestDto = objectMapper.convertValue(repositoryUser, UserRequestDTO.class);
					userResponsetDto.setUserRequestDto(userResquestDto);
					isSuccess = true;
					message = "Retriving user details successfully";
				} else {
					isError = true;
					message = "Email Id doesn't exist";
					if (logger.isDebugEnabled()) {
						logger.debug("Email Id not exist. EmailId={0}", emailId);
					}
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Retriving user details successfully. EmailId={0}", userResponsetDto);
				}
			} catch (Exception exception) {
				isError = true;
				message = "Retriving user details failed.EmailId=" + emailId;
				if (logger.isErrorEnabled()) {
					logger.error("Retriving user details successfully. EmailId=" + emailId, exception);
				}
			}
		} else {
			isError = false;
			message = "Email Id empty or null";
		}
		String status= isSuccess == true ? SUCCESS : FAILURE; 
		userResponsetDto.setStatus(status);
		userResponsetDto.setMessage(message);
		return userResponsetDto;
	}

	public CommonResponseDTO saveCommunityLeaders(CommunityLeadersDTO communityLeadersDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			CommunityLeader communityLeader = objectMapper.convertValue(communityLeadersDTO, CommunityLeader.class);
			communityLeaderRepo.save(communityLeader);
			commonResponseDTO.setStatus(SUCCESS);
			commonResponseDTO.setMessage("Saved Successfully");
		} catch (Exception exception) {
			commonResponseDTO.setMessage("Error while saving community leaders data");
			commonResponseDTO.setStatus(FAILURE);
		}
		return commonResponseDTO;

	}
}
