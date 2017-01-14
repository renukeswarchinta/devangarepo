package com.devangam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.dto.AdvertisementDTO;
import com.devangam.dto.UserRequestDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.service.AdvertisementService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;


/**
 * @author renukeswar
 * This class is responsible to Upload all images corresponding to advertisements , banner images, etc.. 
 *
 */

@RestController
@Slf4j
public class AdvertisementUploadController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@RequestMapping(value="/api/saveAdvertisementDetails",method=RequestMethod.POST)
	public void saveAdvertisement(@RequestParam(value = "files", required = true) List<MultipartFile> files, @RequestParam(value = "advertisementRequestJson", required = true) String advertisementRequestJson){
		AdvertisementDTO advertisementDTO = new AdvertisementDTO();
		advertisementDTO.setAdvertisementRequestJson(advertisementRequestJson);
		advertisementDTO.setMultipartFiles(files);
		advertisementService.saveAdvertisement(advertisementDTO);
	}
	
	@RequestMapping(value="/api/getAdvertisement")
	public @ResponseBody AdvertisementDTO getAdvertisement(){
		return new AdvertisementDTO();
	}
	
	// In thise method we can use java 8 filter to filter out price greater than 1000 to eligible
	@RequestMapping(value="/api/getAdvertisementDetails",method=RequestMethod.GET)
	public Gson getAdvertisementDetails(){
		List<AdvertisementEntity> listOfAdvertisements = advertisementService.getAllAdvertisementDetails();
		List<AdvertisementEntity> adverstimentCostGreaterThan1000 = listOfAdvertisements.stream()
							.filter(advertisement  -> advertisement.getAdvertisementCost() > 1000)
							.collect(Collectors.toList());
		
		List<AdvertisementEntity> adverstimentCostLessThan1000 = listOfAdvertisements.stream()
									.filter(advertisement  -> advertisement.getAdvertisementCost() < 1000)
									.collect(Collectors.toList());
		Gson gson = new Gson();
//		adverstimentCostGreaterThan1000.forEach(advertisement -> System.out.println(advertisement.getAdvertisementCost()));
	//	adverstimentCostLessThan1000.forEach(advertisement -> System.out.println(advertisement.getAdvertisementCost()));
		gson.toJson(adverstimentCostLessThan1000);
		gson.toJson(adverstimentCostGreaterThan1000);
		return gson;
	}
	
	
}
