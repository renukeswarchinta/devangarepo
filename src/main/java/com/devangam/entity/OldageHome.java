package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_OLDAGE_HOME")
public class OldageHome extends HelpingHandCommonAttributes{

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
	
}
