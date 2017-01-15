package com.devangam.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devangam.entity.Education;


public interface EducationHelpingHandRepository extends JpaRepository<Education, Serializable>{
public static final String FIND_ACTIVE_EDUCATION_DETAILS = "SELECT id,NAME,address,description,phone_number,current_education, family_annual_income,family_occupation,recent_merit_scored FROM T_EDUCATION";
	
	@Query(value="SELECT id,NAME,is_enable,address,description,last_update,phone_number,current_education, family_annual_income,family_occupation,recent_merit_scored FROM T_EDUCATION",nativeQuery=true)
	public List<Education> findActiveEducationDetails();
}
