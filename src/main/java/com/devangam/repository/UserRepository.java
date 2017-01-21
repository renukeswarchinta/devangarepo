package com.devangam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devangam.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User  findByMobileNumber(String mobileNo);
	
	@Query("UPDATE User u SET u.active = ?1 WHERE u.mobileNumber = ?2")
	boolean acticateUserByMobileNumber(boolean active , String mobileNo);
}
