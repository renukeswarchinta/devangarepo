package com.devangam.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.devangam.entity.AdvertisementEntity;

@Async
public class SchedularService {

	@Autowired
	private AdvertisementService advertisementService;
	
	public void disableExpiredAdvertisement(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		List<AdvertisementEntity> listOfAdvertisements = advertisementService.getAllAdvertisementDetails();
		//listOfAdvertisements.stream().filter(advertisement -> advertisement.getStartDate() - dateFormat.format(date) )
		
	}
}


