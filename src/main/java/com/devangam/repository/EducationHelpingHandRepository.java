package com.devangam.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devangam.entity.Education;


public interface EducationHelpingHandRepository extends JpaRepository<Education, Serializable>{
	
	@Query(value="SELECT id,NAME,is_enable,address,description,last_update,phone_number,current_education, family_annual_income,family_occupation,recent_merit_scored FROM t_education",nativeQuery=true)
	public List<Education> findActiveEducationDetails();
	
	@Modifying(clearAutomatically = true)
	@Query(value="update Education e set e.isEnable = :disable where e.Id = :helpingHandId")
	public int disableEducationDetailsById(String helpingHandId,int disable);
}
