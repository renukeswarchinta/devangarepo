package com.devangam.dto;
import lombok.Data;
@Data
public class ForgotPasswordDTO {
	private String userName;
	private String otp;
	private String newPassword;
	private String confirmPassword;
	private String verificationType;
}
