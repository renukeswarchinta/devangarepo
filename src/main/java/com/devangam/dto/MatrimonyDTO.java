package com.devangam.dto;
import java.util.ArrayList;
import java.util.List;

import com.devangam.entity.MatrimonyImage;

import lombok.Data;
@Data
public class MatrimonyDTO {
	private int matrimonyId;
	private String cratedFor;
	private String dob;
	private String firstname;
	private String gender;
	private String lastname;
	private String motherToungue;
	private String imageUrl;
	private List<MatrimonyImage> matrimonyImages = new ArrayList<MatrimonyImage>();
}
