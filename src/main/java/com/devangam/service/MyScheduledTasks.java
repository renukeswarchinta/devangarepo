package com.devangam.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.devangam.entity.AdvertisementEntity;
	
	
@Component
public class MyScheduledTasks {

	@Autowired
	private AdvertisementService advertisementService;

    @Scheduled(cron = "0 46 19 * * ?")
    public void disableExpiredActivities() {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
    	List<AdvertisementEntity> ads = advertisementService.getAllAdvertisementDetails();
    	ads.stream().filter(advertisementEntity -> advertisementEntity.getEndDate().after(new Date()))
        .collect(Collectors.toList());
    }
    
  
}