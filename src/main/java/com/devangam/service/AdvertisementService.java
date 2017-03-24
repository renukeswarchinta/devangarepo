package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
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
		if (null != multipartFiles) {
			multipartFiles.forEach(multipartFile -> {
				if (null != multipartFile) {
					AdvertisementEntity advertisementEntity = null;
					String imagePath = null;
					try {
						advertisementEntity = objectMapper.readValue(advertisementDTO.getAdvertisementRequestJson(), AdvertisementEntity.class);
						Document document = new Document(multipartFile.getBytes(), multipartFile.getOriginalFilename(), "", advertisementDirectory);
						advertisementEntity.setImagePath(document.getFileName());
						advertisementRepository.save(advertisementEntity);
						// save image into file system
						fileSystemDocumentService.insert(document);
						commonResponseDTO.setMessage("Ad successfully saved");
						commonResponseDTO.setStatus(SUCCESS);
					} catch (IOException ioException) {
						commonResponseDTO.setMessage("Exeption while saving ads");
						commonResponseDTO.setStatus(FAIL);
					} catch (Exception exception) {
						commonResponseDTO.setMessage("Exeption while saving ads");
						commonResponseDTO.setStatus(FAIL);
					}
				}
			});
		}
		commonResponseDTO.setMessage("Saved Successfully");
		commonResponseDTO.setStatus("200");
		return commonResponseDTO;
	}
	
	public List<AdvertisementEntity> getAllAdvertisementDetails() {
		return  advertisementRepository.findAllActiveAdvertisements();
	}

	public CommonResponseDTO disableAdvertisementDetails(Long id) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			//advertisementRepository.delete(id);
			advertisementRepository.disableAdvertisement(id);
			commonResponseDTO.setMessage("Suucess to update ");
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
		log.info("edit advertisement AdevertisementId=" + advertisementDTO.getId());
		AdvertisementEntity advertisementEntity = objectMapper.readValue(advertisementDTO.getAdvertisementRequestJson(), AdvertisementEntity.class);
		MultipartFile multipartFile = advertisementDTO.getMultipartFiles().size() > 0 ? advertisementDTO.getMultipartFiles().get(0) : null;
		AdvertisementEntity repository  = advertisementRepository.findOne(advertisementEntity.getId());
		String fileName = null;
		if (null != advertisementEntity && null != repository) {
			if (null != multipartFile) {
				fileName = advertisementEntity.getImagePath();
				// Replace/delete the file if already exists
				fileSystemDocumentService.deleteIfExists(advertisementDirectory, fileName);
				Document document = new Document(multipartFile.getBytes(), multipartFile.getOriginalFilename(), "", advertisementDirectory);
				fileSystemDocumentService.insert(document);
				advertisementEntity.setImagePath(document.getFileName());
			} else {
				advertisementEntity.setImagePath(repository.getImagePath());
			}
			advertisementRepository.save(advertisementEntity);
			commonResponseDTO.setMessage("Successfully Updated Ad");
			commonResponseDTO.setStatus(SUCCESS);
		} else {
			commonResponseDTO.setMessage("Faild to edit advertisement");
			commonResponseDTO.setStatus("Fail");
			log.error("edit advertisement id not found" + advertisementDTO.getId());
		}
		return commonResponseDTO;
	}
}