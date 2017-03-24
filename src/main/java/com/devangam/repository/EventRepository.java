package com.devangam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.devangam.entity.AdvertisementEntity;
import com.devangam.entity.Events;

public interface EventRepository extends JpaRepository<Events,Long>{

	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Events c SET c.isActive = 1 WHERE c.id = :id")
	int disableEvents(@Param("id") Long id);

	@Modifying(clearAutomatically = true)
    @Query("SELECT c FROM  Events c WHERE  c.isActive = 0")
	List<Events> findAllActiveEvents();
	
	
}
