package com.devangam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devangam.dto.HelpingHandDonationDetails;
import com.devangam.dto.UserProfileDonationDetails;
import com.devangam.entity.DonationDetails;

public interface DonationDetailsRepository  extends JpaRepository<DonationDetails,Long>{
//,UserProfileDonationRepository{

	@Query(value = "select  d.donation_amt,u.firstname,u.email,u.mobile_number "
			+ "from t_user u "
			+ "join donation_details d "
			+ "on d.user_id = u.user_id "
			+ "where d.helping_hand_id =?0 and d.helping_hand_type = ?1", nativeQuery  = true)
	HelpingHandDonationDetails getDonationDetails(String helpingHandId, String helpingHandType,String userId);
	
	
	@Query(value ="select  e.name,e.phone_number,d.donation_amt,d.helping_hand_type "
						+ " from donation_details d "
						+ " join t_patients e "
						+ " on e.id = d.helping_hand_id  and  d.helping_hand_type ='Patient'" 
						+ " where user_id = :userId",nativeQuery = true)
	List<Object[]> getPatientDonationDetailsByUserId(@Param("userId") long userId);

	@Query(value ="select  e.name,e.phone_number,d.donation_amt,d.helping_hand_type "
			+ "from donation_details d "
			+ "join t_education e "
			+ "on e.id = d.helping_hand_id and  d.helping_hand_type = 'Education' where d.user_id = :userId",nativeQuery = true)
	List<UserProfileDonationDetails> getEducationalDonationDetailsByUserId(@Param("userId") long userId);
	
	@Query(value ="select  e.name,e.phone_number,d.donation_amt,d.helping_hand_type "
			+ "from donation_details d "
			+ "join t_oldage_home e "
			+ "on e.id = d.helping_hand_id and  d.helping_hand_type = 'oldAgeHome'"
			+ " where d.user_id = :userId",nativeQuery = true)
	List<Object[]> getOldAgeHomeDonationDetailsByUserId(@Param("userId") long userId);

	
	DonationDetails findByUserIdAndHelpingHandIdAndHelpingHandType(int userId, int helpingHandId, String helpingHandType);
	
	
	
	
	
}
