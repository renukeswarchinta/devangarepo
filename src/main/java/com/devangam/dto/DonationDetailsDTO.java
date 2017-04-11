package com.devangam.dto;

import lombok.Data;

@Data
public class DonationDetailsDTO {
	private int id;
	private int helpingHandId;
	private int userId;
	private String helpingHandType;
	private double amountReceived;
	private String paymentId;
	private String emailId;
	private String phoneNumber;
}
