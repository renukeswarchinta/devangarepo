package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devangam.archive.Document;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.KalyanaMandapasDTO;
import com.devangam.entity.KalyanaMandapasEntity;
import com.devangam.repository.KalyanaMandapasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class KalyanaMandapasService {

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${kalyanamandapas.directory}")
	private String kalyanaMandapasDirectory;

	@Autowired
	private KalyanaMandapasRepository kalyanaMandapasRepository;

	@Autowired
	private FileSystemDocumentService fileSystemDocumentService;

	public CommonResponseDTO saveKalayanMandapasInfo(KalyanaMandapasDTO kalyanaMandapasDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			KalyanaMandapasEntity kalyanaMandapas = objectMapper.readValue(kalyanaMandapasDTO.getKalyanaMandapasInfo(),
					KalyanaMandapasEntity.class);
			Document document = new Document(kalyanaMandapasDTO.getMultipartFile().getBytes(),
					kalyanaMandapasDTO.getMultipartFile().getOriginalFilename(), "", kalyanaMandapasDirectory);
			kalyanaMandapas.setImagePath(document.getFileName());
			kalyanaMandapasRepository.save(kalyanaMandapas);
			fileSystemDocumentService.insert(document);
			commonResponseDTO.setMessage("Kalyana mandapam successfully saved");
			commonResponseDTO.setStatus(SUCCESS);
		} catch (Exception e) {
			commonResponseDTO.setMessage("Kalyana mandapam failed to saved");
			commonResponseDTO.setStatus(FAIL);
		}
		return commonResponseDTO;
	}
}
