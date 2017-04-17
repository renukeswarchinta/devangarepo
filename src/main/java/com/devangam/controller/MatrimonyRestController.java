package com.devangam.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.MatrimonyProfileDTO;
import com.devangam.dto.PremiumUserDTO;
import com.devangam.entity.PremiumUser;
import com.devangam.entity.User;
import com.devangam.service.MatrimonyService;
import com.devangam.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MatrimonyRestController {
	
	@Autowired
	private MatrimonyService matrimonyService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(value = "/api/user/getMatrimonyList", method = RequestMethod.GET)
	public List<MatrimonyProfileDTO> getMatrimonyList(){
		log.info("start matrimony list");
		return matrimonyService.getMatrimonyList();
	}
	
	@RequestMapping(value = "/api/user/getPaymentDetailsForMatrimony", method = RequestMethod.GET)
	public String getPaymentDetails(@RequestParam("paymentId") final String paymentId){
		//,@RequestParam PremiumUserDTO premiumUserDTO
		Payment payment = null;
		RazorpayClient razorpay = new RazorpayClient("rzp_test_z5OTBfUB0Ell1B", "6LjhikpeDyI34iM7tlxTzxba");
		try {
			payment = razorpay.Payments.fetch(paymentId);
			PremiumUserDTO  premiumUserDTO = new PremiumUserDTO();
			premiumUserDTO.setPremiumAmount(1000);
			premiumUserDTO.setPremiumDuration("02-03-2017 02:22:12"); //dd-MM-yyyy HH:mm:ss
			premiumUserDTO.setPremiumExpiredOn("02-03-2017 02:22:12");
			premiumUserDTO.setPremiumPackage("Yearly");
			premiumUserDTO.setPremiumType("Gold");
			String status = payment.get("status");
			if(status.equals("authorized")){
				// user id from UI, amount i can get from paymnet,premium duration from UI,
				//primium expired date from UI,package type from UI,
				User userEntity = userService.findUserEntity(payment.get("email"));
				PremiumUser pm = objectMapper.convertValue(premiumUserDTO, PremiumUser.class);
				userEntity.setPremiumUser(pm);;
				userService.updateUserEntity(userEntity);
			}
		} catch (RazorpayException e) {
		  // Handle Exception 
		    System.out.println(e.getMessage());
		}
		return payment.toString();
	}
	@RequestMapping(value = "/api/user/getPremiumUserDetailsByUserId", method = RequestMethod.GET)
	public @ResponseBody PremiumUser getPremiumUserDetailsByUserId(@RequestParam("email") String email){
		User userEntity = userService.findUserEntity("email");
		return userEntity.getPremiumUser();
	}
	
	@RequestMapping(value = "/api/user/disableMatrimonyDetails", method = RequestMethod.GET)
	public @ResponseBody CommonResponseDTO disableMatrimonyDetails(@RequestParam("email") String email){
		return matrimonyService.disableMatrimonyDetails(email);
	}
	
	
}