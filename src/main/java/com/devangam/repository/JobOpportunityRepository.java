package com.devangam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.JobOpportunities;

public interface JobOpportunityRepository extends JpaRepository<JobOpportunities, Serializable>{

}
