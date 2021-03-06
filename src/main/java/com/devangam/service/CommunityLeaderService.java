package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devangam.archive.Document;
import com.devangam.dto.AdvertisementDTO;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.CommunityLeadersDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.entity.CommunityLeader;
import com.devangam.entity.Education;
import com.devangam.repository.AdvertisementRepository;
import com.devangam.repository.CommunityLeaderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommunityLeaderService {
	
	@Autowired
	private CommunityLeaderRepository communityLeaderRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Value("${advertisement.directory}")
	private String communityLeadersDirectory;
	@Autowired
    private FileSystemDocumentService fileSystemDocumentService;

	/*public CommonResponseDTO  saveCommunityLeaders(AdvertisementDTO advertisementDTO) {
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
						fileSystemDocumentService.insert(new Document(multipartFile.getBytes(),multipartFile.getOriginalFilename(),"",communityLeadersDirectory));
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
	}*/
	
	public List<CommunityLeader> getCommunityLeaders() {
		return  communityLeaderRepository.findAll();
	}

	public CommonResponseDTO updateCommunityLeaderDetails(CommunityLeadersDTO communityLeadersDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
				CommunityLeader communityLeader = communityLeaderRepository.findOne(communityLeadersDTO.getId());
				communityLeader.setCurrentDesignation(communityLeadersDTO.getCurrentDesignation());
				communityLeader.setDescription(communityLeadersDTO.getCurrentDesignation());
				communityLeader.setName(communityLeadersDTO.getName());
				communityLeaderRepository.save(communityLeader);
				commonResponseDTO.setMessage("Success");
				
		}catch(Exception e){
			commonResponseDTO.setMessage("Failed to update ");
		}
		return commonResponseDTO;
	
	}

	public CommonResponseDTO deleteCommunityLeaderDetails(String id) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			communityLeaderRepository.delete(Long.valueOf(id));
			commonResponseDTO.setMessage("Failed to delete ");
			commonResponseDTO.setStatus(SUCCESS);
		} catch (Exception exception) {
			commonResponseDTO.setMessage("Failed to update ");
			log.error("disable advertisement failed",exception);
			commonResponseDTO.setStatus(FAIL);
		}
		return commonResponseDTO;

	}

	
	
}
