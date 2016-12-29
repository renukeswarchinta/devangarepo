package com.devangam.security.service;

import java.io.Serializable;

import com.devangam.dto.CommonResponseDTO;

public class JwtAuthenticationResponse extends CommonResponseDTO implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private String token;

	public JwtAuthenticationResponse() {
	}

	public JwtAuthenticationResponse(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
