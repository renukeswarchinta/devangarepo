package com.devangam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.Events;

public interface EventRepository extends JpaRepository<Events,Long>{

}
