package com.devangam.dto;

import lombok.Data;

@Data
public class LocationDTO {
	private int locationId;
	private String city;
	private String country;
	private int pincode;
	private String state;
}
