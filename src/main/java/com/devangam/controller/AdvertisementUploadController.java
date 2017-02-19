package com.devangam.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.dto.AdvertisementDTO;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.service.AdvertisementService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
	public @ResponseBody CommonResponseDTO saveAdvertisement(@RequestParam(value = "files", required = false) List<MultipartFile> files, @RequestParam(value = "advertisementRequestJson", required = true) String advertisementRequestJson){
		AdvertisementDTO advertisementDTO = new AdvertisementDTO();
		advertisementDTO.setAdvertisementRequestJson(advertisementRequestJson);
		advertisementDTO.setMultipartFiles(files);
		return advertisementService.saveAdvertisement(advertisementDTO);
	}
	
	@RequestMapping(value="/api/getAdvertisement")
	public @ResponseBody AdvertisementDTO getAdvertisement(){
		return new AdvertisementDTO();
	}
	
	// In thise method we can use java 8 filter to filter out price greater than 1000 to eligible
	@RequestMapping(value="/api/getAdvertisementDetails",method=RequestMethod.GET)
	public @ResponseBody List<AdvertisementEntity> getAdvertisementDetails(){
		return advertisementService.getAllAdvertisementDetails();
	}
	
	@RequestMapping(value ="/api/editAdevertisement",method=RequestMethod.POST)
 	public @ResponseBody CommonResponseDTO editAdevertisement(@RequestParam(value = "files", required = false) List<MultipartFile> files, @RequestParam(value = "advertisementRequestJson", required = true) String advertisementRequestJson) throws JsonParseException, JsonMappingException, IOException{
		AdvertisementDTO advertisementDTO = new AdvertisementDTO();
		advertisementDTO.setAdvertisementRequestJson(advertisementRequestJson);
		advertisementDTO.setMultipartFiles(files);
		return  advertisementService.editAdevertisement(advertisementDTO);
 	}
	
	@RequestMapping(value = "/api/disableAdvertisementDetails", method = RequestMethod.POST)
	public @ResponseBody CommonResponseDTO disableAdvertisementDetails(@RequestParam("id") long id) {
		return advertisementService.disableAdvertisementDetails(id);

	}
	
}
