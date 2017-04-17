package com.devangam.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.RangeException;

import com.devangam.client.razorpay.RazorpayHttpClient;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.DonationDetailsDTO;
import com.devangam.dto.HelpingHandDonationDetails;
import com.devangam.entity.DonationDetails;
import com.devangam.entity.User;
import com.devangam.repository.DonationDetailsRepository;
import com.devangam.repository.UserRepository;
import com.devangam.utils.HelpingHandType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.Payment;

import lombok.extern.slf4j.Slf4j;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;

@Service
@Slf4j
public class DonationDetailsImpl {

	@Autowired
	private DonationDetailsRepository donationRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RazorpayHttpClient razorpayHttpClient;

	public DonationDetails checkIfUserDonatedForHelpingHand(int userId, int helpingHandId, String helpingHandType) {
		return donationRepository.findByUserIdAndHelpingHandIdAndHelpingHandType(userId, helpingHandId, helpingHandType);
	}

	public CommonResponseDTO saveDonationDetails(DonationDetailsDTO donationDetailsDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			User user = userRepository.findByUsername(donationDetailsDTO.getEmailId());
			donationDetailsDTO.setUserId(user.getUserId());

			
			String paymentId = donationDetailsDTO.getPaymentId();
			DonationDetails donationDetails = objectMapper.convertValue(donationDetailsDTO, DonationDetails.class);
			DonationDetails dbDonationDetails = checkIfUserDonatedForHelpingHand(user.getUserId(),donationDetailsDTO.getHelpingHandId(), donationDetailsDTO.getHelpingHandType());
			if (dbDonationDetails != null) {
				double amtReceived = donationDetailsDTO.getAmountReceived() + dbDonationDetails.getAmountReceived();
				dbDonationDetails.setAmountReceived(amtReceived);
				dbDonationDetails.setPaymentId(donationDetailsDTO.getPaymentId());
				donationRepository.save(dbDonationDetails);
			}else{
				donationRepository.save(donationDetails);
			}

			/*if (StringUtils.isNotBlank(paymentId) && donationDetailsDTO.getUserId() > 0	&& donationDetailsDTO.getHelpingHandId() > 0) {
				Payment razorPayment = razorpayHttpClient.getPaymentById(paymentId);
				int amount = razorPayment.get("amount");
				String razorPaymentId = razorPayment.get("id");
				if (paymentId.equalsIgnoreCase(razorPaymentId)) {
					donationRepository.save(dbDonationDetails);
				}
				commonResponseDTO.setMessage("Donation Payment Successully created.");
				commonResponseDTO.setStatus(SUCCESS);
			} else {
				log.error("Required Donation Details are missing" + donationDetailsDTO.toString());
				commonResponseDTO.setMessage("Donation Payment Failed");
				commonResponseDTO.setStatus(FAIL);
			}*/
			String helpingHandType = donationDetailsDTO.getHelpingHandType();
			HelpingHandType type = HelpingHandType.valueOf(helpingHandType);
			//sendEmailToUserRegardingDonations()
			
		} catch (RangeException rangeException) {
			log.error(rangeException.getMessage(), rangeException);
			commonResponseDTO.setMessage("Donation Payment Failed");
			commonResponseDTO.setStatus(FAIL);
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
			commonResponseDTO.setMessage("Donation Payment Failed");
			commonResponseDTO.setStatus(FAIL);
		}
		return commonResponseDTO;
	}

	/*
	 * private DonationDetails checkIfUserDonatedForHelpingHand(int userId, int
	 * helpingHandId, String helpingHandType) { DonationDetails donations =
	 * donationRepository.findByUserIdAndHelpingIdAndHelpingType(userId,
	 * helpingHandId,helpingHandType);
	 * 
	 * }
	 */
	public HelpingHandDonationDetails getDonationDetailsById(String helpingHandId, String helpingHandType,
			String userId) {

		return donationRepository.getDonationDetails(helpingHandId, helpingHandType, userId);
	}

}
