package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_PATIENTS")
public class Patients extends HelpingHandCommonAttributes{
	

	@Column(name="DISEASE_NAME")
	private String suffereingFromdisease;
	
	@Column(name="CURRENT_HEALTH_CONDITION")
	private String currentHealthCondition;
	
	@Column(name="PATIENT_AGE")
	private String patientAge;

	
	
	public String getSuffereingFromdisease() {
		return suffereingFromdisease;
	}

	public void setSuffereingFromdisease(String suffereingFromdisease) {
		this.suffereingFromdisease = suffereingFromdisease;
	}

	public String getCurrentHealthCondition() {
		return currentHealthCondition;
	}

	public void setCurrentHealthCondition(String currentHealthCondition) {
		this.currentHealthCondition = currentHealthCondition;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((currentHealthCondition == null) ? 0 : currentHealthCondition.hashCode());
		result = prime * result + ((patientAge == null) ? 0 : patientAge.hashCode());
		result = prime * result + ((suffereingFromdisease == null) ? 0 : suffereingFromdisease.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patients other = (Patients) obj;
		if (currentHealthCondition == null) {
			if (other.currentHealthCondition != null)
				return false;
		} else if (!currentHealthCondition.equals(other.currentHealthCondition))
			return false;
		if (patientAge == null) {
			if (other.patientAge != null)
				return false;
		} else if (!patientAge.equals(other.patientAge))
			return false;
		if (suffereingFromdisease == null) {
			if (other.suffereingFromdisease != null)
				return false;
		} else if (!suffereingFromdisease.equals(other.suffereingFromdisease))
			return false;
		return true;
	}

}
