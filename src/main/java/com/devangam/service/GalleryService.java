package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.archive.Document;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.GalleryDTO;
import com.devangam.entity.AdvertisementEntity;
import com.devangam.entity.GalleryEntity;
import com.devangam.repository.GalleryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Transactional
public class GalleryService {
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${gallery.directory}")
	private String galleryDirectory;
	
	@Autowired
    private FileSystemDocumentService fileSystemDocumentService;
	@Autowired
	private GalleryRepository galleryRepository;
	
	public CommonResponseDTO saveGallery(GalleryDTO galleryDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		List<MultipartFile> multipartFiles = galleryDTO.getListOfMultipartFiles();
		if (null != multipartFiles) {
			multipartFiles.forEach(multipartFile -> {
				if (null != multipartFile) {
					GalleryEntity galleryEntity = null;
					String imagePath = null;
					try {
						galleryEntity = objectMapper.readValue(galleryDTO.getRequestJson(), GalleryEntity.class);
						Document document = new Document(multipartFile.getBytes(), multipartFile.getOriginalFilename(), "", galleryDirectory);
						galleryEntity.setImagePath(document.getFileName());
						galleryRepository.save(galleryEntity);
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
	
	public List<GalleryEntity> getGalleryImages() {
		return  galleryRepository.findAll();
	}
	public CommonResponseDTO deleteGalleryImages(Long id) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			galleryRepository.delete(id);
			commonResponseDTO.setMessage("Deleted successfully ");
			commonResponseDTO.setStatus(SUCCESS);
		} catch (Exception exception) {
			commonResponseDTO.setMessage("Failed to update ");
			commonResponseDTO.setStatus(FAIL);
		}
		return commonResponseDTO;

	}
}
