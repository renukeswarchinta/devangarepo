package com.devangam.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.LocationDTO;
import com.devangam.dto.MatrimonyDTO;
import com.devangam.dto.UserRequestDTO;
import com.devangam.dto.UserResponseDTO;
import com.devangam.entity.User;
import com.devangam.entity.VerificationToken;
import com.devangam.security.JwtTokenUtil;
import com.devangam.security.JwtUser;
import com.devangam.service.RegistrationService;
import com.devangam.service.UserService;
import com.devangam.utils.DevangamProperty;

@RestController
public class UserRestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public JwtUser getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		return user;
	}
	@RequestMapping(value = "api/getUserList", method = RequestMethod.GET)
	public @ResponseBody List<User> getUserList() {
		return userService.getUserList();
	}
	@RequestMapping(value = "/api/user/matrimonyUserRegistation", method = RequestMethod.POST)
	public @ResponseBody CommonResponseDTO matrimonyUserRegistation(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "userRequestJson", required = true) String userRequestJson) {
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setMultipartFile(file);
		userRequestDTO.setUserRequestJson(userRequestJson);
		return registrationService.saveMatrimonyUser(userRequestDTO);
	}
	
	@RequestMapping(value = "/api/user/signupUser", method = RequestMethod.POST)
	public @ResponseBody CommonResponseDTO signupUser(@RequestBody UserRequestDTO userRequestDto) {
		return registrationService.saveUserFromUserRequest(userRequestDto);
	}
	
	@RequestMapping(value = "/api/admin/createAdminUser", method = RequestMethod.POST)
	public @ResponseBody CommonResponseDTO createAdminUser(@RequestBody UserRequestDTO userRequestDto) {
		return registrationService.saveAdminUser(userRequestDto);
	}

	@RequestMapping(value = "userdto", method = RequestMethod.GET)
	public @ResponseBody UserRequestDTO getUserDTO() {
		logger.debug("Process user dto start..debug");
		logger.info("Process user dto start..INFO");
		logger.warn("Process user dto start..WARN");
		UserRequestDTO userRequestDto = new UserRequestDTO();
		userRequestDto.setMatrimony(new MatrimonyDTO());
		userRequestDto.setLocation(new LocationDTO());
		return userRequestDto;
	}

	@RequestMapping(path = "/api/user/optMatrimonyRegistation", method = RequestMethod.POST)
	public @ResponseBody CommonResponseDTO optMatrimonyRegistation(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "userRequestJson", required = true) String userRequestJson) {
		UserRequestDTO userRequestDTO = new UserRequestDTO();
		userRequestDTO.setMultipartFile(file);
		userRequestDTO.setUserRequestJson(userRequestJson);
		return registrationService.saveOptUserMatrimony(userRequestDTO);
	}

	@RequestMapping(value = "/api/getUserDetails/{emailId}", method = RequestMethod.GET)
	public @ResponseBody UserResponseDTO getUserDetails(@PathVariable String emailId) {
		logger.debug("Fetch UserDetails start. EmailID=" + emailId);
		// Need to check how we can make this user object in session object.and
		// retrive the same with out hitting the database
		return registrationService.getUserDetails(emailId);
	}
	
	// User profile management; 
	//Show what are the trasanctions done for all helping hand types
	// Jobs uploaded if any
	// Matrimony details if any ... (This can be taken later)
	public void getUserProfileDetails(String emailId){
		
		
	}
	

	/**	Method is used to send password on forgot password performed action
	 * @param emailId
	 * @return Response either success or failure
	 */
	@RequestMapping(value = "/api/forgetPassword/{emailId}", method = RequestMethod.GET)
	public @ResponseBody UserResponseDTO forgetPassword(@PathVariable String emailId) {
		logger.debug("Fetch UserDetails start. EmailID=" + emailId);
		UserResponseDTO userDetails =  registrationService.getUserDetails(emailId);
		UserRequestDTO userRequestDTO = userDetails.getUserRequestDto();
		String password = userRequestDTO.getPassword();
		//Send this password thru email to user.
		
		return userDetails;
	}
	@RequestMapping(value = "/api/registrationConfirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(final HttpServletRequest request, final Model model,
			@RequestParam("token") final String token) {
		final Locale locale = request.getLocale();

		final VerificationToken verificationToken = registrationService.getVerificationToken(token);
		if (verificationToken == null) {
			final String message = DevangamProperty.getInstance().getProperties("auth.message.invalidToken");
			model.addAttribute("message", message);
			return new ModelAndView("redirect:/badUser.html?lang=" + locale.getLanguage());
		}

		final User userEntity = verificationToken.getUser();
		final Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("message", DevangamProperty.getInstance().getProperties("auth.message.invalidToken"));
			model.addAttribute("expired", true);
			model.addAttribute("token", token);
			return new ModelAndView("redirect:/badUser.html?lang=" + locale.getLanguage());
		}

		User persistObj = userService.findUserEntity(userEntity.getEmail());
		persistObj.setActive(true);
		userService.updateUserEntity(persistObj);

		model.addAttribute("message", DevangamProperty.getInstance().getProperties("auth.message.validToken"));
		//return "redirect:{/authenticate1}";
		return new ModelAndView(new RedirectView("/"));
	}
	
	@RequestMapping(value = "/api/user/verifyEmail", method = RequestMethod.GET)
	public @ResponseBody CommonResponseDTO verifyMobileNumber(@RequestParam String token,
			@RequestParam String email) {
		logger.info("Request received for Get My Profile");
		CommonResponseDTO commonResponseDTO = registrationService.verifyMobileNumberByOtp(token, email);
		return commonResponseDTO;
	}
	
	@RequestMapping(value="/api/user/updateUserProfile")
	public @ResponseBody CommonResponseDTO updateUserProfile(@RequestBody UserRequestDTO userRequestDto){
		CommonResponseDTO commonResponseDTO= registrationService.updateUserProfile(userRequestDto);
		return commonResponseDTO;
	}

	
	
}