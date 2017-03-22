package com.devangam.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.devangam.entity.AdvertisementEntity;

public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Serializable>{

	@Transactional
	@Modifying(clearAutomatically = true)
    @Query("UPDATE AdvertisementEntity c SET c.expired = 1 WHERE c.id = :id")
	int disableAdvertisement(@Param("id") Long id);

	@Modifying(clearAutomatically = true)
    @Query("SELECT c FROM  AdvertisementEntity c WHERE  c.expired = 0")
	List<AdvertisementEntity> findAllActiveAdvertisements();

	
}
