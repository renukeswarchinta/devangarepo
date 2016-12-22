package com.devangam.dto;
import lombok.Data;
@Data
public class PremiumUserDTO {
	private int premiumAmount;
	private String premiumDuration;
	private String premiumExpiredOn;
	private String premiumPackage;
	private String premiumType;
}
