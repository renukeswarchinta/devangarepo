package com.devangam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devangam.entity.Patients;

public interface PatientHelpingHandRepository extends JpaRepository<Patients, Serializable>{

	@Modifying(clearAutomatically = true)
	@Query(value="update Patients e set e.isEnable = :disable where e.Id = :helpingHandId")
	public int disablePatientDetailsById(String helpingHandId, int disable);

}
