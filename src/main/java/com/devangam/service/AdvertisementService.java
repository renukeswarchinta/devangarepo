package com.devangam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.AdvertisementDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.repository.AdvertisementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdvertisementService {
	
	@Autowired
	private AdvertisementRepository advertisementRepository;
	@Autowired
	private ObjectMapper objectMapper;

	public void saveAdvertisement(AdvertisementDTO advertisementDTO) {
		 AdvertisementEntity advertisementEntity = objectMapper.convertValue(advertisementDTO, AdvertisementEntity.class);
		 advertisementRepository.save(advertisementEntity);
	}

	public List<AdvertisementEntity> getAllAdvertisementDetails() {
		return  advertisementRepository.findAll();
	}

	
}
