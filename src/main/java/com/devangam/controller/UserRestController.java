package com.devangam.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.LocationDTO;
import com.devangam.dto.MatrimonyDTO;
import com.devangam.dto.PersonalDetailDTO;
import com.devangam.dto.PremiumUserDTO;
import com.devangam.dto.ProfessionalDetailsDTO;
import com.devangam.dto.ReligionDetailsDTO;
import com.devangam.dto.UserRequestDTO;
import com.devangam.dto.UserResponseDTO;
import com.devangam.security.JwtTokenUtil;
import com.devangam.security.JwtUser;
import com.devangam.service.FileSystemDocumentService;
import com.devangam.service.RegistrationService;

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
    private RegistrationService registrationService;

    
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    
    
    @RequestMapping(value = "/api/signupUser", method = RequestMethod.POST)
    public @ResponseBody CommonResponseDTO signupUser(
            @RequestParam(value="file" , required=false) MultipartFile file ,
            @RequestParam(value="userRequestJson", required=true) String userRequestJson) {
    	UserRequestDTO userRequestDTO = new UserRequestDTO();
    	userRequestDTO.setMultipartFile(file);
    	userRequestDTO.setUserRequestJson(userRequestJson);
    	return registrationService.createUser(userRequestDTO);
    }
    
    @RequestMapping(value = "userdto", method = RequestMethod.GET)
	public @ResponseBody UserRequestDTO getUserDTO() {
    	logger.debug("Process user dto start..debug");
    	logger.info("Process user dto start..INFO");
    	logger.warn("Process user dto start..WARN");
    	UserRequestDTO userRequestDto= new UserRequestDTO();
    	userRequestDto.setMatrimony(new MatrimonyDTO());
    	userRequestDto.setLocation(new LocationDTO());
    	userRequestDto.setPersonalDetail(new PersonalDetailDTO());
    	userRequestDto.setProfessionalDetail(new ProfessionalDetailsDTO());
    	userRequestDto.setReligionDetail(new ReligionDetailsDTO());
    	userRequestDto.setPremiumUser(new PremiumUserDTO());
    	return userRequestDto;
    }
    
    @RequestMapping(path = "/api/optMatrimonyRegistation", method = RequestMethod.POST)
   	public @ResponseBody CommonResponseDTO optMatrimonyRegistation(@RequestBody UserRequestDTO userRequestDto) {
       	return registrationService.createUserMatrimony(userRequestDto);
       }

    @RequestMapping(value = "/api/getUserDetails/{emailId}", method = RequestMethod.GET)
	public @ResponseBody UserResponseDTO getUserDetails(@PathVariable String emailId) {
		logger.debug("Fetch UserDetails start. EmailID=" + emailId);
    	//Need to check how we can make this user object in session object.and retrive the same with out hitting the database
    	return registrationService.getUserDetails(emailId);
    }
    
    @RequestMapping(value = "/api/fileupload", method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile inputFile) {
    	System.out.println("--------");
    }
    
    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public @ResponseBody void handleFileUpload(
            @RequestParam("file") MultipartFile file) throws IOException {
        
    	String originalFilename = file.getOriginalFilename();
    	logger.info("handleFileUpload----->"+originalFilename);
    	byte[] bytes = file.getBytes();
    	
       /* try {
            Document document = new Document(file.getBytes(), file.getOriginalFilename(), date, person );
            getArchiveService().save(document);
            return document.getMetadata();
        } catch (RuntimeException e) {
            LOG.error("Error while uploading.", e);
            throw e;
        } catch (Exception e) {
            LOG.error("Error while uploading.", e);
            throw new RuntimeException(e);
        }  */    
    }
    
    
    
}