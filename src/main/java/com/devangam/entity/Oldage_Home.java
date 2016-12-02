package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_OLDAGE_HOME")
public class Oldage_Home extends HelpingHandCommonAttributes{

	@Transient
	private static final long serialVersionUID = -3621010469526215357L;
	
	@Column(name="PERSONS_PRESENT_IN_OLDAGEHOME")
	private int noOfPeople;
	@Column(name="GOVT_ID")
	private String oldageHomeGovtId;
	
	
	public int getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public String getOldageHomeGovtId() {
		return oldageHomeGovtId;
	}
	public void setOldageHomeGovtId(String oldageHomeGovtId) {
		this.oldageHomeGovtId = oldageHomeGovtId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + noOfPeople;
		result = prime * result + ((oldageHomeGovtId == null) ? 0 : oldageHomeGovtId.hashCode());
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
		Oldage_Home other = (Oldage_Home) obj;
		if (noOfPeople != other.noOfPeople)
			return false;
		if (oldageHomeGovtId == null) {
			if (other.oldageHomeGovtId != null)
				return false;
		} else if (!oldageHomeGovtId.equals(other.oldageHomeGovtId))
			return false;
		return true;
	}
	
}
