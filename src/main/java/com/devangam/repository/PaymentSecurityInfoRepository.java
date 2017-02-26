package com.devangam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.PaymentSecurityInfo;

public interface PaymentSecurityInfoRepository extends JpaRepository<PaymentSecurityInfo, Long> {

}
