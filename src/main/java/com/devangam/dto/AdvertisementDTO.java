package com.devangam.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AdvertisementDTO {
	private int id;
	private String imagePath;
	// is it whether BENNER, ADVERTISEMENT, SELF etc.. //SELF means ads uploaded by admin for self purpose
	private String advertisementType;
	private Date startDate;
	private Date endDate;
	private double advertisementCost;
	private boolean isExpired;
	

}
