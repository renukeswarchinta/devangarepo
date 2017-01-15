package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAILURE;
import static com.devangam.constants.DevangamConstants.SUCCESS;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.archive.Document;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.CommunityLeadersDTO;
import com.devangam.dto.Mail;
import com.devangam.dto.UserRequestDTO;
import com.devangam.dto.UserResponseDTO;
import com.devangam.entity.AuthorityName;
import com.devangam.entity.CommunityLeader;
import com.devangam.entity.MatrimonyImage;
import com.devangam.entity.Role;
import com.devangam.entity.User;
import com.devangam.entity.VerificationToken;
import com.devangam.enums.MatrimonyImageType;
import com.devangam.repository.CommunityLeaderRepository;
import com.devangam.repository.RoleRepository;
import com.devangam.repository.UserRepository;
import com.devangam.repository.VerificationTokenRepository;
import com.devangam.utils.DevangamProperty;
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
	@Autowired
    private VerificationTokenRepository tokenRepository;
	@Autowired
	private EmailService emailService;
	@Value("${matrimony.directory}")
	private String matrimonyDirectory;
	 @Value("${devangam.home.url}")
	 private String devangamHomeURL;
	 
	 
	 public CommonResponseDTO saveAdminUser(UserRequestDTO userRequestDto){
			CommonResponseDTO userResponseDto = new CommonResponseDTO();
			// TODO : Pre validation check and Required filed validation is required
			User repositoryUser = null;
			String message = null;
			String status = null;
			boolean isError = false;
			boolean isSuccess = false;
			if(null == userRequestDto || StringUtils.isEmpty(userRequestDto.getEmail())){
				isError = true;
				message = "User request is empty or null";
			} else {
				try {
					repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
					if (null == repositoryUser) {
						///User repositoryUser = null;
						User user = convertUserRequestDtoToUser(userRequestDto);
						if (null != user) {
							//user.setActive(Boolean.TRUE);
							user.setActive(Boolean.FALSE);
							user.setCreatedDate(new Date());
							user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
							if (user.getRoles() != null) {
								user.setRoles(userRolesRepository.findAll());
							}
							user.setUsername(userRequestDto.getEmail());
							repositoryUser = userRepository.save(user);
						}
						//saveUserFromUserDto(userRequestDto);
						isSuccess = true;
						message = "Successfully registered";
						if (logger.isInfoEnabled()) {
							logger.info("Successfully created user. EmailId=" + userRequestDto.getEmail());
						}
					} else {
						isError = true;
						message = "This email address is already in use";
					}
				} catch (Exception exception) {
					isError = true;
					message = "User creation failed. Please try after some time.";
					if (logger.isErrorEnabled()) {
						logger.error("Create user failed. EmailId=" + userRequestDto.getEmail(), exception);
					}
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
	
	public CommonResponseDTO saveMatrimonyUser(UserRequestDTO userJsonRequestDto) {
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
				//save image into file system
				MultipartFile multipartFile = userRequestDto.getMultipartFile();
				if(null != multipartFile) {
					String key = Instant.now().getEpochSecond() + "_" + "MPS";
					userRequestDto.getMatrimony().getMatrimonyImages().add(new MatrimonyImage(key,"/"+ key+"/"+ multipartFile.getOriginalFilename(),MatrimonyImageType.PROFILE.name()));
					repositoryUser = saveUserFromUserDto(userRequestDto);
					if(null != repositoryUser){
						fileSystemDocumentService.insert(new Document(multipartFile.getBytes(),multipartFile.getOriginalFilename(),String.valueOf(key),matrimonyDirectory));
					}
				}
				isSuccess = true;
				message = "Successfully registered";
				if (logger.isInfoEnabled()) {
					logger.info("Successfully created user. EmailId=" + userRequestDto.getEmail());
				}
			} else {
				isError = true;
				message = "This email address is already in use";
			}
		} catch (Exception exception) {
			isError = true;
			message = "User creation failed. Please try after some time.";
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
	
	public VerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
	public CommonResponseDTO saveUserFromUserRequest(UserRequestDTO userRequestDto) {
		CommonResponseDTO userResponseDto = new CommonResponseDTO();
		// TODO : Pre validation check and Required filed validation is required
		
		User repositoryUser = null;
		String message = null;
		String status = null;
		boolean isError = false;
		boolean isSuccess = false;
		if(null == userRequestDto || StringUtils.isEmpty(userRequestDto.getEmail())){
			isError = true;
			message = "User request is empty or null";
		} else {
			try {
				repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
				if (null == repositoryUser) {
					saveUserFromUserDto(userRequestDto);
					isSuccess = true;
					message = "Successfully registered";
					if (logger.isInfoEnabled()) {
						logger.info("Successfully created user. EmailId=" + userRequestDto.getEmail());
					}
				} else {
					isError = true;
					message = "This email address is already in use";
				}
			} catch (Exception exception) {
				isError = true;
				message = "User creation failed. Please try after some time.";
				if (logger.isErrorEnabled()) {
					logger.error("Create user failed. EmailId=" + userRequestDto.getEmail(), exception);
				}
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
	
	public User saveUserFromUserDto(UserRequestDTO userRequestDto){
		User repositoryUser = null;
		User user = convertUserRequestDtoToUser(userRequestDto);
		if (null != user) {
			//user.setActive(Boolean.TRUE);
			user.setActive(Boolean.FALSE);
			user.setCreatedDate(new Date());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			if (user.getRoles() != null) {
				Role role = userRolesRepository.findOne(Integer.valueOf((AuthorityName.ROLE_USER.getRole())));
				user.getRoles().add(role);
			}
			user.setUsername(userRequestDto.getEmail());
			repositoryUser = userRepository.save(user);
			
			Mail mail = new Mail();
			 mail.setTemplateName(EmailService.VERIFY_EMAIL);
			 mail.setMailTo(userRequestDto.getEmail());
			 Map<String,String> map =new HashMap<String,String>();
			 final String token = UUID.randomUUID().toString();
			 createVerificationTokenForUser(user, token);
			 final String confirmationUrl = devangamHomeURL + "/api/registrationConfirm?token=" + token;
			 map.put("firstName",  userRequestDto.getFirstname());
			 map.put("link", confirmationUrl);
			 mail.setValueMap(map);
			 emailService.sendMail(mail);
		}
		return repositoryUser;
	}
	
	private void createVerificationTokenForUser(User user, String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
	}

	public User convertUserRequestDtoToUser(UserRequestDTO userRequestDto){
		User user = null;
		try {
			user = objectMapper.convertValue(userRequestDto, User.class);
		} catch (IllegalArgumentException illegalArgumentException) {
			logger.error("Convert UserRequestDto to User failed.", illegalArgumentException);
		}
		return user;
	}
	

	// From UI when user wants to register for Matrimony then we need to create
	// all details like matrimony, location etc...after that we set
	// all other objects from user object
	public CommonResponseDTO saveOptUserMatrimony(UserRequestDTO userRequestDto) {
		CommonResponseDTO commonResponseDto = new CommonResponseDTO();
		String message = null;
		String status = null;
		try {
			if (userRequestDto.isMatrimonyUser()) {
				User repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
				User user = convertUserRequestDtoToUser(userRequestDto);
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
					//Add this to recover password
					userResquestDto.setPassword(bCryptPasswordEncoder.encode(repositoryUser.getPassword()));
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
