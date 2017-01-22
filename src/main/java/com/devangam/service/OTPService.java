package com.devangam.service;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.EmailOrMobileOtpDTO;
import com.devangam.entity.Otp;
import com.devangam.entity.User;
import com.devangam.repository.OtpRepository;
import com.devangam.utils.OTPUtil;
import com.devangam.utils.PasswordProtector;

@Service
@Transactional
public class OTPService {
	@Autowired
	private OtpRepository otpRepository;

	public Otp createOTP(Otp otp) {
		Otp repositoryOtp = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 30);
		otp.setValidUpto(new Date(calendar.getTimeInMillis()));
		otp.setOtp(PasswordProtector.encrypt(OTPUtil.generateIntToken()));
		otp.setStatus("WAITING");
		repositoryOtp = otpRepository.save(otp);
		return repositoryOtp;
	}
	
	public Otp getOTP(Otp otp) {
		Otp otpEntity = otpRepository.findByVerificationId(otp.getVerificationId());
		if (otpEntity != null) {
			otp.setOtp(PasswordProtector.decrypt(otpEntity.getOtp()));
			otp.setType(otpEntity.getType());
			otp.setStatus(otpEntity.getStatus());
		}
		return otp;
	}
	
	public Otp verifyOTP(Otp otp) {
		Otp otpEntity = otpRepository.findByVerificationId(otp.getVerificationId());
		if (otpEntity != null && "WAITING".equals(otpEntity.getStatus())) {
			if (/*otp.getType().equals(otpEntity.getType())
					&&*/ PasswordProtector.encrypt(otp.getOtp()).equals(otpEntity.getOtp())) {
				if (otpEntity.getValidUpto().getTime() > System.currentTimeMillis()) {
					otp.setStatus("VERIFIED");
				} else {
					otp.setStatus("EXPIRED");
				}
				otpEntity.setStatus(otp.getStatus());
				otpRepository.saveAndFlush(otpEntity);
			} else {
				otp.setStatus("INVALID");
			}
		} else {
			otp.setStatus("INVALID");
		}

		return otp;
	}
	
	public Otp reCreateOTP(Otp otp) {
		Otp otpEntity = otpRepository.findByVerificationId(otp.getVerificationId());
		if (otpEntity != null && otpEntity.getOtpId() > 0) {
			otpEntity.setVerificationId(otp.getVerificationId());
			otpEntity.setType(otp.getType());
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 30);
			otpEntity.setValidUpto(new Date(calendar.getTimeInMillis()));
			String generateIntToken = OTPUtil.generateIntToken();
			otp.setOtp(generateIntToken);
			otpEntity.setOtp(PasswordProtector.encrypt(generateIntToken));
			// TODO Send Email Notification to user
			// TODO send SMS Verification to user
			otpEntity.setStatus("WAITING");
			otpRepository.save(otpEntity);
		} else {
			otp = createOTP(otp);
		}
		return otp;
	}
	
	public void sendSMSForVerification(EmailOrMobileOtpDTO emailOrMobileOtpDTO){
		User user = emailOrMobileOtpDTO.getUser();
		Otp mobileOTP = new Otp();
		mobileOTP.setUserId(user.getUserId());
		mobileOTP.setVerificationId(user.getMobileNumber());
		mobileOTP.setType("MOBILE");
		reCreateOTP(mobileOTP);
		// TODO : sms gateway integration
	  /*	SMS sms = new SMS();
		sms.setPhoneNumber(smsOTP.getVerificationId());
		String otp = PasswordProtector.decrypt(smsOTP.getOtp());
		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("OTP", otp);
		sms.setTemplate(SMSService.VERIFY_NO);
		sms.setValueMap(valueMap);
		smsService.sendSMS(sms);*/	
		
		
	}

}
