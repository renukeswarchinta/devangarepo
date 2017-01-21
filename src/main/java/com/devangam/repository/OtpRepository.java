package com.devangam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {

	Otp findByVerificationId(String verificationId);

}
