package com.devangam.dto;

import lombok.Data;
@Data
public class PatientDetailsDTO  extends HelpingHandCommonDTO{

	private String suffereingFromdisease;
	private String currentHealthCondition;
	private String patientAge;
}
