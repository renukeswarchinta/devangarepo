package com.devangam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devangam.entity.OldageHome;

public interface OldAgeHomeHelpingHandRpository extends JpaRepository<OldageHome, Serializable>{
	@Modifying(clearAutomatically = true)
	@Query(value="update OldageHome e set e.isEnable = :disable where e.Id = :helpingHandId")
	public int disableOldAgeHomeDetailsById(String helpingHandId, int disable);

}
