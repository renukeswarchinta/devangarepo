package com.devangam.service;

import java.io.IOException;
import static com.devangam.constants.DevangamConstants.SUCCESS;
import static com.devangam.constants.DevangamConstants.FAIL;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.archive.Document;
import com.devangam.dto.AdvertisementDTO;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.repository.AdvertisementRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

	public CommonResponseDTO saveAdvertisement(AdvertisementDTO advertisementDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		List<MultipartFile> multipartFiles = advertisementDTO.getMultipartFiles();
		boolean editFlow = advertisementDTO.isEditFlow();
		if (null != multipartFiles) {
			multipartFiles.forEach(multipartFile -> {
				if (null != multipartFile) {
					AdvertisementEntity advertisementEntity = null;
					String imagePath = null;
					try {
						advertisementEntity = objectMapper.readValue(advertisementDTO.getAdvertisementRequestJson(), AdvertisementEntity.class);
						if(editFlow) {
							imagePath = advertisementEntity.getImagePath();
						} else {
							String uuid = String.valueOf(Instant.now().getEpochSecond());
							imagePath = "/"+ uuid + "_"+ multipartFile.getOriginalFilename();
						}
						advertisementEntity.setImagePath(imagePath);
						advertisementRepository.save(advertisementEntity);
						// save image into file system
						try {
							fileSystemDocumentService
									.insert(new Document(multipartFile.getBytes(), imagePath, "", advertisementDirectory));
						} catch (IOException e) {
							commonResponseDTO.setMessage("Exeption while saving ads");
							commonResponseDTO.setStatus("500");
						}
					} catch (IOException ioException) {
						
					}
				}
			});
		}
		commonResponseDTO.setMessage("Saved Successfully");
		commonResponseDTO.setStatus("200");
		return commonResponseDTO;
	}
	
	public List<AdvertisementEntity> getAllAdvertisementDetails() {
		return  advertisementRepository.findAll();
	}

	public CommonResponseDTO disableAdvertisementDetails(long id) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			advertisementRepository.delete(id);
			commonResponseDTO.setMessage("Failed to delete ");
			commonResponseDTO.setStatus(SUCCESS);
		} catch (Exception exception) {
			commonResponseDTO.setMessage("Failed to update ");
			log.error("disable advertisement failed",exception);
			commonResponseDTO.setStatus(FAIL);
		}
		return commonResponseDTO;

	}

	public CommonResponseDTO editAdevertisement(AdvertisementDTO advertisementDTO) throws JsonParseException, JsonMappingException, IOException {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		log.debug("edit advertisement AdevertisementId=" + advertisementDTO.getId());
		AdvertisementEntity advertisementEntity = objectMapper.readValue(advertisementDTO.getAdvertisementRequestJson(), AdvertisementEntity.class);
		AdvertisementEntity repository  = advertisementRepository.findOne(advertisementEntity.getId());
		if (null != advertisementEntity && null != repository) {
			advertisementDTO.setEditFlow(Boolean.TRUE);
			commonResponseDTO = saveAdvertisement(advertisementDTO);
		} else {
			commonResponseDTO.setMessage("Faild to edit advertisement");
			commonResponseDTO.setStatus("Fail");
			log.error("edit advertisement id not found" + advertisementDTO.getId());
		}
		return commonResponseDTO;
	}

	
}
