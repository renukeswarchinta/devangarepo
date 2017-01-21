package com.devangam.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OTPUtil {

	public static String generateToken() {
		return String.valueOf(UUID.randomUUID());
	}

	public static String generateIntToken() {
		SecureRandom prng = null;
		try {
			prng = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			log.error("Generate Int Token Failed." ,e);
		}
		int number = prng.nextInt(999999);
		if (number < 99999) {
			number = number + 900000;
		}
		String randomNum = Integer.toString(number);
		return randomNum;
	}

}
