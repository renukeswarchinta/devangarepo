package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOB_OPPRTUNITIES")
public class JobOpportunities {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
	 private int Id ;
	
	@Column(name="jobName")
	private String jobName;
	
	@Column(name="jobDescription")
	private String jobDescription;
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="eligibility")
	private String eligibility;
	
	@Column(name="jobLink")
	private String jobLink;
	
	JobOpportunities(){}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	public String getJobLink() {
		return jobLink;
	}

	public void setJobLink(String jobLink) {
		this.jobLink = jobLink;
	}
	
	
	
}
