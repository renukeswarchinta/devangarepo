package com.devangam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.MatrimonyDTO;
import com.devangam.service.MatrimonyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MatrimonyRestController {
	
	@Autowired
	private MatrimonyService matrimonyService;
	
	@RequestMapping(value = "/api/getMatrimonyList", method = RequestMethod.GET)
	public List<MatrimonyDTO> getMatrimonyList(){
		log.info("start matrimony list");
		return matrimonyService.getMatrimonyList();
	}

}
