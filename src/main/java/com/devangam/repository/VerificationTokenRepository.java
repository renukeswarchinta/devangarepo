package com.devangam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devangam.entity.User;
import com.devangam.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	    VerificationToken findByToken(String token);

	    VerificationToken findByUser(User user);

	    List<VerificationToken> findAllByExpiryDateLessThan(Date now);

	    void deleteByExpiryDateLessThan(Date now);

	    @Modifying
	    @Query("delete from VerificationToken t where t.expiryDate <= ?1")
	    void deleteAllExpiredSince(Date now);

}
