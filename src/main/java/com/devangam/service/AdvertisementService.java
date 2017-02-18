package com.devangam.service;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devangam.archive.Document;
import com.devangam.dto.AdvertisementDTO;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.entity.Education;
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

	public CommonResponseDTO  saveAdvertisement(AdvertisementDTO advertisementDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		advertisementDTO.getMultipartFiles().forEach(multipartFile->{
			if(null != multipartFile) {
				AdvertisementEntity advertisementEntity;
				try {
					advertisementEntity = objectMapper.readValue(advertisementDTO.getAdvertisementRequestJson(), AdvertisementEntity.class);
				
				String uuid = String.valueOf(Instant.now().getEpochSecond());
				//String imagePathKey = "/"+ uuid+"/"+ multipartFile.getOriginalFilename();
				String imagePathKey =  multipartFile.getOriginalFilename();
				advertisementEntity.setImagePath(imagePathKey);
				advertisementRepository.save(advertisementEntity);
				// save image into file system
					try {
						fileSystemDocumentService.insert(new Document(multipartFile.getBytes(),multipartFile.getOriginalFilename(),"",advertisementDirectory));
					} catch (IOException e) {
						commonResponseDTO.setMessage("Exeption while saving ads");
						commonResponseDTO.setStatus("500");
						e.printStackTrace();
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		commonResponseDTO.setMessage("Saved Successfully");
		commonResponseDTO.setStatus("200");
		return commonResponseDTO;
	}
	
	public List<AdvertisementEntity> getAllAdvertisementDetails() {
		return  advertisementRepository.findAll();
	}

	public CommonResponseDTO updateAdvertisementDetails(AdvertisementDTO advertisementDTO) {

		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
				AdvertisementEntity advertisementEntity = advertisementRepository.findOne(advertisementDTO.getId());
				advertisementEntity.setAdvertisementCost(advertisementDTO.getAdvertisementCost());
//				advertisementEntity.setAdvertisementType(advertisementType);
				advertisementEntity.setEndDate(advertisementDTO.getEndDate());
				advertisementEntity.setStartDate(advertisementDTO.getStartDate());
				advertisementRepository.save(advertisementEntity);
				commonResponseDTO.setMessage("Success");
				
		}catch(Exception e){
			commonResponseDTO.setMessage("Failed to update ");
		}
		return commonResponseDTO;
	
	}

	public CommonResponseDTO disableAdvertisementDetails(long id) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
				AdvertisementEntity advertisementEntity = advertisementRepository.findOne(id);
				advertisementEntity.setExpired(true);
				advertisementRepository.save(advertisementEntity);
		}catch(Exception e){
			commonResponseDTO.setMessage("Failed to update ");
		}
		return commonResponseDTO;
		
	}

	
}
