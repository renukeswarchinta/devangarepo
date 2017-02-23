package com.devangam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.Events;

public interface EventRepository extends JpaRepository<Events,Long>{

	
	//List<Events> findActiveEvents();

	
	
}
