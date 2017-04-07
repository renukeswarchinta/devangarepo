package com.devangam.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class GalleryDTO {

	private long id;
	private String galleryName;
	private String imagePath;
	@JsonIgnore
	private String requestJson; 
	@JsonIgnore
	private List<MultipartFile> listOfMultipartFiles;
	/*@JsonIgnore
	private MultipartFile[] listOfMultipartFiles;*/
	@JsonIgnore
	private MultipartFile multipartFiles;
}
