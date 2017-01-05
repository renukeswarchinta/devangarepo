package com.devangam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devangam.dto.AdvertisementDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.service.AdvertisementService;


/**
 * @author renukeswar
 * This class is responsible to Upload all images corresponding to advertisements , banner images, etc.. 
 *
 */

@Controller
public class AdvertisementUploadController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@RequestMapping(value="/api/saveAdvertisementDetails",method=RequestMethod.POST)
	public void saveAdvertisement(AdvertisementDTO advertisementDTO){
		advertisementService.saveAdvertisement(advertisementDTO);
		
		
	}
	
	// In thise method we can use java 8 filter to filter out price greater than 1000 to eligible
	@RequestMapping(value="/api/getAdvertisementDetails",method=RequestMethod.GET)
	public void getAdvertisementDetails(){
		List<AdvertisementEntity> listOfAdvertisements = advertisementService.getAllAdvertisementDetails();
		listOfAdvertisements.stream()
							.filter(advertisement  -> advertisement.getAdvertisementCost() > 1000)
							.collect(Collectors.toList());
							
		
	}
}
