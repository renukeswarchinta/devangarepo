package com.devangam.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.MatrimonyProfileDTO;
import com.devangam.service.MatrimonyService;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MatrimonyRestController {
	
	@Autowired
	private MatrimonyService matrimonyService;
	
	@RequestMapping(value = "/api/user/getMatrimonyList", method = RequestMethod.GET)
	public List<MatrimonyProfileDTO> getMatrimonyList(){
		log.info("start matrimony list");
		return matrimonyService.getMatrimonyList();
	}
	
	@RequestMapping(value = "api/user/getPaymentDetailsForMatrimony", method = RequestMethod.GET)
	public String getPaymentDetails(@RequestParam("paymentId") final String paymentId){
		Payment payment = null;
		RazorpayClient razorpay = new RazorpayClient("rzp_test_z5OTBfUB0Ell1B", "6LjhikpeDyI34iM7tlxTzxba");
		try {
			payment = razorpay.Payments.fetch(paymentId);
			String status = payment.get("status");
			if(status.equals("authorized")){
				// user id from UI, amount i can get from paymnet,premium duration from UI,
				//primium expired date from UI,package type from UI,
				
			}
		} catch (RazorpayException e) {
		  // Handle Exception 
		    System.out.println(e.getMessage());
		}
		return payment.toString();
	}
}