package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.archive.Document;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.CommunityLeadersDTO;
import com.devangam.dto.EmailOrMobileOtpDTO;
import com.devangam.dto.LocationDTO;
import com.devangam.dto.MatrimonyDTO;
import com.devangam.dto.PersonalDetailDTO;
import com.devangam.dto.PremiumUserDTO;
import com.devangam.dto.ProfessionalDetailsDTO;
import com.devangam.dto.ReligionDetailsDTO;
import com.devangam.dto.UserRequestDTO;
import com.devangam.dto.UserResponseDTO;
import com.devangam.entity.AuthorityName;
import com.devangam.entity.CommunityLeader;
import com.devangam.entity.Location;
import com.devangam.entity.Matrimony;
import com.devangam.entity.MatrimonyImage;
import com.devangam.entity.Otp;
import com.devangam.entity.PersonalDetail;
import com.devangam.entity.PremiumUser;
import com.devangam.entity.ProfessionalDetails;
import com.devangam.entity.ReligionDetails;
import com.devangam.entity.Role;
import com.devangam.entity.User;
import com.devangam.entity.VerificationToken;
import com.devangam.enums.MatrimonyImageType;
import com.devangam.repository.CommunityLeaderRepository;
import com.devangam.repository.RoleRepository;
import com.devangam.repository.UserRepository;
import com.devangam.repository.VerificationTokenRepository;
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
	@Autowired
	private OTPService otpService;

	public CommonResponseDTO saveAdminUser(UserRequestDTO userRequestDto) {
		CommonResponseDTO userResponseDto = new CommonResponseDTO();
		User repositoryUser = null;
		String message = null;
		String status = null;
		boolean isError = false;
		boolean isSuccess = false;
		if (null == userRequestDto || StringUtils.isEmpty(userRequestDto.getEmail())) {
			isError = true;
			message = "User request is empty or null";
		} else {
			try {
				repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
				if (null == repositoryUser) {
					/// User repositoryUser = null;
					User user = convertUserRequestDtoToUser(userRequestDto);
					if (null != user) {
						// user.setActive(Boolean.TRUE);
						user.setActive(Boolean.FALSE);
						user.setCreatedDate(new Date());
						user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
						if (user.getRoles() != null) {
							user.setRoles(userRolesRepository.findAll());
						}
						user.setUsername(userRequestDto.getEmail());
						repositoryUser = userRepository.save(user);
					}
					// saveUserFromUserDto(userRequestDto);
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
			status = FAIL;
		}
		userResponseDto.setStatus(status);
		userResponseDto.setMessage(message);
		return userResponseDto;

	}

	public CommonResponseDTO saveMatrimonyUser(UserRequestDTO userJsonRequestDto) {
		CommonResponseDTO userResponseDto = new CommonResponseDTO();
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
				// save image into file system
				MultipartFile multipartFile = userRequestDto.getMultipartFile();
				if (null != multipartFile) {
					String key = Instant.now().getEpochSecond() + "_" + "MPS";
					userRequestDto.getMatrimony().getMatrimonyImages().add(new MatrimonyImage(key,
							"/" + key + "/" + multipartFile.getOriginalFilename(), MatrimonyImageType.PROFILE.name()));
					repositoryUser = saveUserFromUserDto(userRequestDto);
					if (null != repositoryUser) {
						fileSystemDocumentService.insert(new Document(multipartFile.getBytes(),
								multipartFile.getOriginalFilename(), String.valueOf(key), matrimonyDirectory));
					}
				} else {
					repositoryUser = saveUserFromUserDto(userRequestDto);
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
			status = FAIL;
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

		User repositoryUser = null;
		String message = null;
		String status = null;
		boolean isError = false;
		boolean isSuccess = false;
		if (null == userRequestDto || StringUtils.isEmpty(userRequestDto.getEmail())) {
			isError = true;
			message = "User request is empty or null";
		} else {
			try {
				repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
				if (null == repositoryUser) {
					saveUserFromUserDto(userRequestDto);
					// send OTP to registered mobile or Email
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
			status = FAIL;
		}
		userResponseDto.setStatus(status);
		userResponseDto.setMessage(message);
		return userResponseDto;
	}

	public User saveUserFromUserDto(UserRequestDTO userRequestDto) {
		User repositoryUser = null;
		User user = convertUserRequestDtoToUser(userRequestDto);
		if(null != userRequestDto.getMatrimony()){
			PersonalDetail pd = convertPersonDetailsDtoToPersonEntity(userRequestDto.getPersonalDetail());
			Location location = convertLocationDetailsFromJsonToEntity(userRequestDto.getLocation());
			PremiumUser pu = convertPremiumUserFromJsonToEntity(userRequestDto.getPremiumUser());
			ReligionDetails rd = convertReligionDetailsFromJsonToEntity(userRequestDto.getReligionDetail());
			ProfessionalDetails profDetails = convertProffesionalDetailsToEntity(userRequestDto.getProfessionalDetail());
			Matrimony matrimony = convertMatrimonyDtoToEntity(userRequestDto.getMatrimony());
			user.setMatrimony(matrimony);
			user.setPersonalDetail(pd);
			user.setLocation(location);
			user.setPremiumUser(pu);
			user.setReligionDetail(rd);
			user.setProfessionalDetail(profDetails);
			user.setMatrimonyUser(Boolean.TRUE);
		}
		if (null != user) {
			user.setCreatedDate(new Date());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			if (user.getRoles() != null) {
				Role role = userRolesRepository.findOne(Integer.valueOf((AuthorityName.ROLE_USER.getRole())));
				user.getRoles().add(role);
			}
			user.setUsername(userRequestDto.getEmail());
			repositoryUser = userRepository.save(user);
			 emailService.sendEmailForVerification(new EmailOrMobileOtpDTO(repositoryUser));
			 otpService.sendSMSForVerification(new EmailOrMobileOtpDTO(repositoryUser));
		}
		return repositoryUser;
	}
	
	private Matrimony convertMatrimonyDtoToEntity(MatrimonyDTO matrimonyDto) {
		return objectMapper.convertValue(matrimonyDto, Matrimony.class);
	}

	private ProfessionalDetails convertProffesionalDetailsToEntity(ProfessionalDetailsDTO professionalDetail) {
		return objectMapper.convertValue(professionalDetail, ProfessionalDetails.class);
	}

	private ReligionDetails convertReligionDetailsFromJsonToEntity(ReligionDetailsDTO religionDetail) {
		return objectMapper.convertValue(religionDetail, ReligionDetails.class);
	}

	private PremiumUser convertPremiumUserFromJsonToEntity(PremiumUserDTO premiumUser) {
		return objectMapper.convertValue(premiumUser, PremiumUser.class);
	}

	private Location convertLocationDetailsFromJsonToEntity(LocationDTO location) {
		return objectMapper.convertValue(location, Location.class);
	}

	private PersonalDetail convertPersonDetailsDtoToPersonEntity(PersonalDetailDTO personalDetail) {
		return objectMapper.convertValue(personalDetail, PersonalDetail.class);
	}

	private void createVerificationTokenForUser(User user, String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);
	}

	public User convertUserRequestDtoToUser(UserRequestDTO userRequestDto) {
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
	public CommonResponseDTO saveOptUserMatrimony(UserRequestDTO userJsonRequestDto) {
		CommonResponseDTO commonResponseDto = new CommonResponseDTO();
		String message = null;
		String status = null;
		UserRequestDTO userRequestDto = null;
		try {
			userRequestDto = objectMapper.readValue(userJsonRequestDto.getUserRequestJson(), UserRequestDTO.class);
			userRequestDto.setMultipartFile(userJsonRequestDto.getMultipartFile());
			MultipartFile multipartFile = userRequestDto.getMultipartFile();
			String key = Instant.now().getEpochSecond() + "_" + "MPS";
			if (null != multipartFile) {
				userRequestDto.getMatrimony().getMatrimonyImages().add(new MatrimonyImage(key,
						"/" + key + "/" + multipartFile.getOriginalFilename(), MatrimonyImageType.PROFILE.name()));
			}
			if (userRequestDto.isMatrimonyUser()) {
				User repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
				User user = convertUserRequestDtoToUser(userRequestDto);
				PersonalDetail pd = convertPersonDetailsDtoToPersonEntity(userRequestDto.getPersonalDetail());
				Location location = convertLocationDetailsFromJsonToEntity(userRequestDto.getLocation());
				PremiumUser pu = convertPremiumUserFromJsonToEntity(userRequestDto.getPremiumUser());
				ReligionDetails rd = convertReligionDetailsFromJsonToEntity(userRequestDto.getReligionDetail());
				ProfessionalDetails profDetails = convertProffesionalDetailsToEntity(userRequestDto.getProfessionalDetail());
				Matrimony matrimony = convertMatrimonyDtoToEntity(userRequestDto.getMatrimony());
				repositoryUser.setMatrimony(matrimony);
				repositoryUser.setMatrimonyUser(true);
				repositoryUser.setLocation(location);
				repositoryUser.setPersonalDetail(pd);
				repositoryUser.setProfessionalDetail(profDetails);
				repositoryUser.setReligionDetail(rd);
				repositoryUser.setPremiumUser(pu);
				repositoryUser = userRepository.save(repositoryUser);
				if (null != repositoryUser) {
					fileSystemDocumentService.insert(new Document(multipartFile.getBytes(),
							multipartFile.getOriginalFilename(), String.valueOf(key), matrimonyDirectory));
				}
				status = SUCCESS;
				message = "Successfully registered";
				if (logger.isInfoEnabled()) {
					logger.info("Successfully created MatrimonyUser. EmailId=" + userRequestDto.getEmail());
				}
			}
		} catch (Exception exception) {
			status = FAIL;
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
					MatrimonyDTO matrimonyDTO = objectMapper.convertValue(repositoryUser.getMatrimony(), MatrimonyDTO.class);
					userResquestDto.setMatrimony(matrimonyDTO);
					userResponsetDto.setUserRequestDto(userResquestDto);
					// Add this to recover password
					//userResquestDto.setPassword(bCryptPasswordEncoder.encode(repositoryUser.getPassword()));
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
		String status = isSuccess == true ? SUCCESS : FAIL;
		userResponsetDto.setStatus(status);
		userResponsetDto.setMessage(message);
		return userResponsetDto;
	}

	@Value("${communityLeaders.directory}")
	private String communityLeadersDirectory;

	public CommonResponseDTO saveCommunityLeaders(CommunityLeadersDTO communityLeadersDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		CommunityLeader communityLeaderEntity;
		try {
		communityLeaderEntity = objectMapper.readValue(communityLeadersDTO.getCommunityLeadersRequestJson(), CommunityLeader.class);
		String imagePathKey = communityLeadersDTO.getMultipartFiles().getOriginalFilename();
		communityLeaderEntity.setImagePath(imagePathKey);
		communityLeaderRepo.save(communityLeaderEntity);
		try {
			fileSystemDocumentService.insert(new Document(communityLeadersDTO.getMultipartFiles().getBytes(),
					communityLeadersDTO.getMultipartFiles().getOriginalFilename(), "", communityLeadersDirectory));
		} catch (IOException e) {
			commonResponseDTO.setMessage("Exeption while saving community leaders");
			commonResponseDTO.setStatus("500");
			e.printStackTrace();
		}} catch (IOException e1) {
			commonResponseDTO.setMessage("Exeption while saving community leaders");
			commonResponseDTO.setStatus("500");
			e1.printStackTrace();
		}
		commonResponseDTO.setMessage("Saved Successfully");
		commonResponseDTO.setStatus("200");
		return commonResponseDTO;
	}

	public boolean verifyMobileNO(String mobileNo) {
		boolean result = true;
		User user = userRepository.findByMobileNumber(mobileNo);
		if (user != null && user.getEmail() != null) {
			result = false;
		}
		return result;
	}

	public CommonResponseDTO verifyMobileNumberByOtp(String token, String mobileNumber) {
		Otp otp = new Otp();
		otp.setOtp(token);
		otp.setVerificationId(mobileNumber);
		otp.setType("MOBILE");
		CommonResponseDTO response = new CommonResponseDTO();
		try {
			otp = otpService.verifyOTP(otp);
			if ("VERIFIED".equals(otp.getStatus())) {
				int value = userRepository.updateUser(Boolean.TRUE, mobileNumber);
				logger.info("Update User activity: ACTIVE=" + value);
				response.setMessage("Succcessfully verified Mobile Number");
				response.setStatus(SUCCESS);
			} else if ("EXPIRED".equals(otp.getStatus())) {
				response.setMessage("OTP Expired. Please generate another OTP");
				response.setStatus(FAIL);
			} else {
				response.setMessage("Invalid OTP");
				response.setStatus(FAIL);
			}
		} catch (Exception exception) {
			response.setMessage("System error occured while verifying OTP");
			response.setStatus(FAIL);
			logger.error("Failed OTP Mobile Number verification.", exception);
		}
		return response;

	}

	public CommonResponseDTO updateUserProfile(UserRequestDTO userRequestDto) {
		CommonResponseDTO response = new CommonResponseDTO();
		try {
			User repositoryUser = userRepository.findByUsername(userRequestDto.getEmail());
			if (null != repositoryUser) {
				User user = convertUserRequestDtoToUser(userRequestDto);
				repositoryUser.setFirstname(user.getFirstname());
				repositoryUser.setLastname(user.getLastname());
				repositoryUser.setCountry(user.getCountry());
				repositoryUser.setDistrict(user.getDistrict());
				repositoryUser.setState(user.getState());
				if (null != userRequestDto.getMatrimony()) {

					Matrimony repositoryMatrimony = repositoryUser.getMatrimony();
					Matrimony matrimony = convertMatrimonyDtoToEntity(userRequestDto.getMatrimony());
					repositoryMatrimony.setFirstname(matrimony.getFirstname());
					repositoryMatrimony.setLastname(matrimony.getLastname());
					repositoryMatrimony.setGender(matrimony.getGender());
					repositoryMatrimony.setMotherToungue(matrimony.getMotherToungue());
					repositoryMatrimony.setDob(matrimony.getDob());
					
					PersonalDetail personalDetail = convertPersonDetailsDtoToPersonEntity(userRequestDto.getPersonalDetail());
					PersonalDetail repositoryPersonalDetails = repositoryUser.getPersonalDetail();
					repositoryPersonalDetails.setMaritalStatus(personalDetail.getMaritalStatus());
					repositoryPersonalDetails.setDisability(personalDetail.getDisability());
					repositoryPersonalDetails.setFamilyStatus(personalDetail.getFamilyStatus());
					repositoryPersonalDetails.setFamilyType(personalDetail.getFamilyType());
					repositoryPersonalDetails.setFamilyValues(personalDetail.getFamilyValues());
					repositoryPersonalDetails.setHeight(personalDetail.getHeight());
					
					Location location = convertLocationDetailsFromJsonToEntity(userRequestDto.getLocation());
					Location repositoryLocation = repositoryUser.getLocation();
					repositoryLocation.setCountry(location.getCountry());
					repositoryLocation.setState(location.getState());
					repositoryLocation.setCity(location.getCity());
					repositoryLocation.setPincode(location.getPincode());
					
					ReligionDetails religionDetails = convertReligionDetailsFromJsonToEntity(userRequestDto.getReligionDetail());
					ReligionDetails repositoryReligionDetail = repositoryUser.getReligionDetail();
					repositoryReligionDetail.setDosham(religionDetails.getDosham());
					repositoryReligionDetail.setGothram(religionDetails.getGothram());
					repositoryReligionDetail.setSubcaste(religionDetails.getSubcaste());
					
					ProfessionalDetails professionalDetails = convertProffesionalDetailsToEntity(userRequestDto.getProfessionalDetail());
					ProfessionalDetails repositoryProfessionalDetails = repositoryUser.getProfessionalDetail();
					repositoryProfessionalDetails.setEmployedIn(professionalDetails.getEmployedIn());
					repositoryProfessionalDetails.setHighestEducaiton(professionalDetails.getHighestEducaiton());
					repositoryProfessionalDetails.setIncome(professionalDetails.getIncome());
					repositoryProfessionalDetails.setOccupation(professionalDetails.getOccupation());
				}
				userRepository.save(repositoryUser);
				response.setStatus(SUCCESS);
				response.setMessage("Successfully updated");
			} else {
				response.setStatus(FAIL);
				response.setMessage("Fail to update profile. Please enter valid email");
			}
		} catch (Exception exception) {
			logger.error("Update user profile failed. EmailId="+userRequestDto.getEmail(), exception);
			response.setStatus(FAIL);
			response.setMessage("Fail to update profile. Please try after some time");
		}
		// Need to send an email with Successfully updated profile.
		return response;
	}

}
