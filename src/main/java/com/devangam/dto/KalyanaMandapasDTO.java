package com.devangam.dto;

import java.util.List;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class KalyanaMandapasDTO {

	private Long id;
	private String kalyanaMandapamName;
	private String imagePath;
	private String kalyanaMandapamAddress;
	private Double kalyanaMandapamRent;
	@JsonIgnore
	private String kalyanaMandapasInfo;
	private MultipartFile multipartFile;
	private String kalyanaMandapamContactInfo;
}
