package com.devangam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.MatrimonyProfileDTO;
import com.devangam.entity.User;
import com.devangam.repository.UserRepository;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;

@Service
public class MatrimonyService {

	@Autowired
	private UserRepository userRepository;

	public List<MatrimonyProfileDTO> getMatrimonyList() {
		List<MatrimonyProfileDTO> matrimonyDtos = new ArrayList<MatrimonyProfileDTO>();
		List<User> repositoryUsers = userRepository.findAll();
		if (null != repositoryUsers) {
			repositoryUsers.forEach(user -> {
				MatrimonyProfileDTO matrimonyProfileDto = new MatrimonyProfileDTO();
				matrimonyProfileDto.setUserId(user.getUserId());
				matrimonyProfileDto.setMatrimony(user.getMatrimony());
				matrimonyProfileDto.setPersonalDetail(user.getPersonalDetail());
				matrimonyProfileDto.setReligionDetails(user.getReligionDetail());
				matrimonyProfileDto.setProfessionalDetails(user.getProfessionalDetail());
				matrimonyProfileDto.setLocation(user.getLocation());
				matrimonyDtos.add(matrimonyProfileDto);
			});
		}
		return matrimonyDtos;
	}

	public CommonResponseDTO disableMatrimonyDetails(String email) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			userRepository.disableMatrimonyUser(false, email);
			commonResponseDTO.setMessage("Successfully disabled Matrimony details for this user");
			commonResponseDTO.setStatus(SUCCESS);
			return commonResponseDTO;
		} catch (Exception e) {
			commonResponseDTO.setStatus(FAIL);
			return commonResponseDTO;
		}

	}
}
