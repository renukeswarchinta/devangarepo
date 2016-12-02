package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Table;

@Table(appliesTo = "T_EDUCATION")
@Entity
public class Education extends HelpingHandCommonAttributes{
	
	@Transient
	private static final long serialVersionUID = -3621010469526215357L;
	
	@Column(name="CURRENT_EDUCATION")
	private String currentEducation;
	
	@Column(name="RECENT_MERIT_SCORED")
	private String merit;
	
	@Column(name="FAMILY_OCCUPATION")
	private String familyStatus;
	
	@Column(name="FAMILY_ANNUAL_INCOME")
	private String familyAnnualIncome;
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentEducation == null) ? 0 : currentEducation.hashCode());
		result = prime * result + ((familyAnnualIncome == null) ? 0 : familyAnnualIncome.hashCode());
		result = prime * result + ((familyStatus == null) ? 0 : familyStatus.hashCode());
		result = prime * result + ((merit == null) ? 0 : merit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Education other = (Education) obj;
		if (currentEducation == null) {
			if (other.currentEducation != null)
				return false;
		} else if (!currentEducation.equals(other.currentEducation))
			return false;
		if (familyAnnualIncome == null) {
			if (other.familyAnnualIncome != null)
				return false;
		} else if (!familyAnnualIncome.equals(other.familyAnnualIncome))
			return false;
		if (familyStatus == null) {
			if (other.familyStatus != null)
				return false;
		} else if (!familyStatus.equals(other.familyStatus))
			return false;
		if (merit == null) {
			if (other.merit != null)
				return false;
		} else if (!merit.equals(other.merit))
			return false;
		return true;
	}

	public String getCurrentEducation() {
		return currentEducation;
	}

	public void setCurrentEducation(String currentEducation) {
		this.currentEducation = currentEducation;
	}

	public String getMerit() {
		return merit;
	}

	public void setMerit(String merit) {
		this.merit = merit;
	}

	public String getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}

	public String getFamilyAnnualIncome() {
		return familyAnnualIncome;
	}

	public void setFamilyAnnualIncome(String familyAnnualIncome) {
		this.familyAnnualIncome = familyAnnualIncome;
	}
	
	
	
	
}
