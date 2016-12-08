package com.devangam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.LocationDTO;
import com.devangam.dto.MatrimonyDTO;
import com.devangam.dto.PersonalDetailDTO;
import com.devangam.dto.ProfessionalDetailsDTO;
import com.devangam.dto.ReligionDetailsDTO;
import com.devangam.dto.UserRequestDTO;
import com.devangam.entity.Matrimony;
import com.devangam.entity.User;
import com.devangam.security.JwtTokenUtil;
import com.devangam.security.JwtUser;
import com.devangam.service.RegistrationService;

@RestController
public class UserRestController {

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
    
    
    @RequestMapping(path = "/api/signupUser", method = RequestMethod.POST)
	public @ResponseBody String signupUser(@RequestBody UserRequestDTO userRequestDTO) {
    	registrationService.createUser(userRequestDTO);
    	return "sucess";
    }
    
    @RequestMapping(value = "userdto", method = RequestMethod.GET)
	public @ResponseBody UserRequestDTO getUserDTO() {
    	UserRequestDTO userRequestDto= new UserRequestDTO();
    	userRequestDto.setMatrimony(new MatrimonyDTO());
    	userRequestDto.setLocation(new LocationDTO());
    	userRequestDto.setPersonalDetail(new PersonalDetailDTO());
    	userRequestDto.setProfessionalDetail(new ProfessionalDetailsDTO());
    	userRequestDto.setReligionDetail(new ReligionDetailsDTO());
    	return userRequestDto;
    }
    
    @RequestMapping(path = "/api/signupUserMatrimony", method = RequestMethod.POST)
   	public @ResponseBody String signupUserMatrimony(@RequestBody Matrimony matrimony) {
       	registrationService.createUserMatrimony(matrimony);
       	return "sucess";
       }

    @RequestMapping(path = "/api/getUserDetails", method = RequestMethod.GET)
	public @ResponseBody String getUserDetails(@RequestParam String emailId) {
    	User user = registrationService.getUserDetails(emailId);
    	boolean isMatrimony = user.isMatrimonyUser();
    	//Need to check how we can make this user object in session object.and retrive the same with out hitting the database
    	if(isMatrimony){
    		//Matrimony matrimony = user.getMatrimony();
    		
    	}
    	
    			
    	return "sucess";
    }
}