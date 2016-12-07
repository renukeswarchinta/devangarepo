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
	public @ResponseBody String signupUser(@RequestBody User userRequestDTO) {
    	registrationService.createUser(userRequestDTO);
    	return "sucess";
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
    
    
    @RequestMapping(value = "userdto", method = RequestMethod.GET)
	public @ResponseBody User getUserDTO() {
    	User user = new User();
    	//user.setMatrimony(new Matrimony());
    	return user;
    }
}