package com.devangam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.GalleryDTO;
import com.devangam.service.GalleryService;

@RestController
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;

	@RequestMapping(path="api/addGalleryImages",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO saveGallery(
			@RequestParam(value = "file", required = false) List<MultipartFile> file, 
			@RequestParam(value = "requestJson", required = true) String requestJson){
		
		GalleryDTO galleryDTO = new GalleryDTO();
		//galleryDTO.setMultipartFiles(file);
		galleryDTO.setListOfMultipartFiles(file);
		galleryDTO.setRequestJson(requestJson);
		return galleryService.saveGallery(galleryDTO);
	} 
	
}
