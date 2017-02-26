package com.devangam.security.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.entity.PaymentSecurityInfo;
import com.devangam.repository.PaymentSecurityInfoRepository;

@Service
@Transactional
public class PaymentSecurityInfoService {
	
	@Autowired
	public PaymentSecurityInfoRepository paymentSecurityInfoRepository;

	public PaymentSecurityInfo getPaymentSecurityInfo(){
		PaymentSecurityInfo paymentSecurityInfo = null;
		List<PaymentSecurityInfo> paymentSecurityInfoList = paymentSecurityInfoRepository.findAll();
		paymentSecurityInfo = paymentSecurityInfoList.get(0);
		return paymentSecurityInfo;
	}
	
	public void CreatePaymentSecurityInfo(){
		
	}
	
	
}
