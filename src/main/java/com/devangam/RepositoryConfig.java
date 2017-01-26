package com.devangam;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.devangam.entity.AdvertisementEntity;
import com.devangam.entity.CommunityLeader;
import com.devangam.entity.Education;
import com.devangam.entity.Events;
import com.devangam.entity.JobOpportunities;
import com.devangam.entity.Matrimony;
import com.devangam.entity.OldageHome;
import com.devangam.entity.Patients;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
	 @Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config)
	    {
	        super.configureRepositoryRestConfiguration(config);
	        config.exposeIdsFor(Education.class,
	        					Events.class,
	        					AdvertisementEntity.class,
	        					Matrimony.class,
	        					OldageHome.class,
	        					Patients.class,
	        					JobOpportunities.class,
	        					CommunityLeader.class);
	    }
  
}