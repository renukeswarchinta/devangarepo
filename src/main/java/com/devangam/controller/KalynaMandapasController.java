package com.devangam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.KalyanaMandapasDTO;
import com.devangam.service.KalyanaMandapasService;



@RestController
public class KalynaMandapasController {

	@Autowired
	private KalyanaMandapasService  kalyanaMandapasService;
	
	
	@PostMapping("/saveKalynaMandapasInfo")
	public  CommonResponseDTO saveKalynaMandapasInfo(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "kalyanaMandapaInfo", required = true) String kalyanaMandapaInfo){
		KalyanaMandapasDTO kalyanaMandapasDTO = new KalyanaMandapasDTO();
		kalyanaMandapasDTO.setMultipartFile(file);
		kalyanaMandapasDTO.setKalyanaMandapasInfo(kalyanaMandapaInfo);
		
	return null;
	}

}
