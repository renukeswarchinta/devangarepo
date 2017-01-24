package com.devangam.dto;
import java.util.List;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
public class CommunityLeadersDTO {
	private long id;
	private String name;
	private String imagePath;
	private String currentDesignation;
	private String description;
	@JsonIgnore
	private String communityLeadersRequestJson;
	@JsonIgnore
	private MultipartFile multipartFiles;
	
	
}
