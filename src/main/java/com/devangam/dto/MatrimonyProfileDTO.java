package com.devangam.dto;

import com.devangam.entity.Location;
import com.devangam.entity.Matrimony;
import com.devangam.entity.PersonalDetail;
import com.devangam.entity.ProfessionalDetails;
import com.devangam.entity.ReligionDetails;

import lombok.Data;
@Data
public class MatrimonyProfileDTO {
	private int userId;
	private Matrimony matrimony;
	private ProfessionalDetails professionalDetails;
	private ReligionDetails religionDetails;
	private PersonalDetail personalDetail;
	private Location location;
}
