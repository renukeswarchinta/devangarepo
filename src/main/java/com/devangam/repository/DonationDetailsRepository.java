package com.devangam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.DonationDetails;

public interface DonationDetailsRepository  extends JpaRepository<DonationDetails,Long>{

}
