package com.devangam.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AdvertisementDTO {
	private int id;
	private String imagePath;
	// is it whether BENNER, ADVERTISEMENT etc..
	private String advertisementType;
	private Date startDate;
	private Date endDate;
	private double advertisementCost;


}
