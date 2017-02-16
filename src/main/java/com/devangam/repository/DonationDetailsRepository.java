package com.devangam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devangam.dto.HelpingHandDonationDetails;
import com.devangam.entity.DonationDetails;

public interface DonationDetailsRepository  extends JpaRepository<DonationDetails,Long>{

	@Query(value = "select  d.donation_amt,u.firstname,u.email,u.mobile_number "
			+ "from t_user u "
			+ "join donation_details d "
			+ "on d.user_id = u.user_id "
			+ "where d.helping_hand_id =?0 and d.helping_hand_type = ?1", nativeQuery  = true)
	HelpingHandDonationDetails getDonationDetails(String helpingHandId, String helpingHandType);

}
