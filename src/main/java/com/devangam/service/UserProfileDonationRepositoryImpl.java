package com.devangam.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.devangam.dto.UserProfileDonationDetails;
import com.devangam.repository.UserProfileDonationRepository;

public class UserProfileDonationRepositoryImpl implements UserProfileDonationRepository {
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public List<Object> getUserProfileDonations() {

		/*String jpaql="SELECT DonationDetails d JOIN Education e ON d.helpingHandId = e.Id";
		Query createQuery = em.createQuery(jpaql);
		List<Object> resultList = createQuery.getResultList();
		resultList.stream().forEach((object) -> { 
			System.out.println("  "+object);
		}
		);
		return null;*/
		
		 List<UserProfileDonationDetails> employees = em.createNamedQuery("getDonationDetails", UserProfileDonationDetails.class)
				  .getResultList();
		 employees.stream().forEach((i)->{
			 System.out.println(i.getDonation_amt());
		 });;
        
		 return null;
	}
	

}
