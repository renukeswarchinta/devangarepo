package com.devangam.dto;

import com.devangam.entity.Otp;
import com.devangam.entity.User;

import lombok.Data;

@Data
public class EmailOrMobileOtpDTO {
	private Otp otp;
	private User user;

	public EmailOrMobileOtpDTO() {
	}

	public EmailOrMobileOtpDTO(User user, Otp otp) {
		this.otp = otp;
		this.user = user;
	}

	public EmailOrMobileOtpDTO(User user) {
		this.user = user;
	}
}
