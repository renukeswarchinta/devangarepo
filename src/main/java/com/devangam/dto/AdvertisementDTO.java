package com.devangam.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class AdvertisementDTO {
	private int id;
	private String imagePath;
	// is it whether BANNER, ADVERTISEMENT, SELF etc.. //SELF means ads uploaded by admin for self purpose
	private String advertisementType;
	private Date startDate;
	private Date endDate;
	private double advertisementCost;
	private boolean expired;
	@JsonIgnore
	private String advertisementRequestJson;
	@JsonIgnore
	private List<MultipartFile> multipartFiles;
	private MultipartFile multipartFile;
	private boolean editFlow;
}
