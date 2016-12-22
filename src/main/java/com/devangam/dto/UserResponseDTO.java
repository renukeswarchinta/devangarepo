package com.devangam.dto;

import lombok.Data;

@Data
public class UserResponseDTO extends CommonResponseDTO {
	private UserRequestDTO userRequestDto;
}
