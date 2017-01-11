package com.devangam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.MatrimonyDTO;
import com.devangam.entity.Matrimony;
import com.devangam.repository.MatrimonyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MatrimonyService {
	
	@Autowired
	private MatrimonyRepository matrimonyRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

	public List<MatrimonyDTO> getMatrimonyList() {
		List<MatrimonyDTO> matrimonyDtos = new ArrayList<MatrimonyDTO>();
		List<Matrimony> matrimonyList = matrimonyRepository.findAll();
		matrimonyList.forEach(item->{
			MatrimonyDTO matrimonyDto = objectMapper.convertValue(item, MatrimonyDTO.class);
			matrimonyDtos.add(matrimonyDto);
		});
		return matrimonyDtos;
	}
}