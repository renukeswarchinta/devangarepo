package com.devangam.controller;

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
import com.devangam.dto.GalleryDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.entity.GalleryEntity;
import com.devangam.service.GalleryService;

@RestController
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;

	@RequestMapping(value="/api/addGalleryImages",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO saveGallery(
			@RequestParam(value = "files", required = false) List<MultipartFile> files, 
			@RequestParam(value = "requestJson", required = true) String requestJson){
		
		
		
		GalleryDTO galleryDTO = new GalleryDTO();
		//galleryDTO.setMultipartFiles(file);
		galleryDTO.setListOfMultipartFiles(files);
		galleryDTO.setRequestJson(requestJson);
		return galleryService.saveGallery(galleryDTO);
	
	}
	
	@RequestMapping(value="/api/getGalleryImages",method=RequestMethod.GET)
	public @ResponseBody List<GalleryEntity> getGalleryImages(){
		return galleryService.getGalleryImages();
	}
	@RequestMapping(value = "/api/deleteGalleryImages", method = RequestMethod.DELETE)
	public @ResponseBody CommonResponseDTO deleteGalleryImages(@RequestParam("id") Long id) {
		return galleryService.deleteGalleryImages(id);
	}
}
