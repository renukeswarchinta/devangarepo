package com.devangam.service;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devangam.archive.Document;
import com.devangam.dto.AdvertisementDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.repository.AdvertisementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdvertisementService {
	
	@Autowired
	private AdvertisementRepository advertisementRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Value("${advertisement.directory}")
	private String advertisementDirectory;
	@Autowired
    private FileSystemDocumentService fileSystemDocumentService;

	public void saveAdvertisement(AdvertisementDTO advertisementDTO) {
		advertisementDTO.getMultipartFiles().forEach(multipartFile->{
			if(null != multipartFile) {
				AdvertisementEntity advertisementEntity = objectMapper.convertValue(advertisementDTO, AdvertisementEntity.class);
				String uuid = String.valueOf(Instant.now().getEpochSecond());
				//String imagePathKey = "/"+ uuid+"/"+ multipartFile.getOriginalFilename();
				String imagePathKey =  multipartFile.getOriginalFilename();
				advertisementEntity.setImagePath(imagePathKey);
				advertisementRepository.save(advertisementEntity);
				// save image into file system
					try {
						fileSystemDocumentService.insert(new Document(multipartFile.getBytes(),multipartFile.getOriginalFilename(),"",advertisementDirectory));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		 
	}
	
	public List<AdvertisementEntity> getAllAdvertisementDetails() {
		return  advertisementRepository.findAll();
	}

	
}
