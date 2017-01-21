package com.devangam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devangam.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User  findByMobileNumber(String mobileNo);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE User u SET u.active = :active WHERE u.email =:email")
	int updateUser(@Param("active") boolean active , @Param("email") String email);
}
