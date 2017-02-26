package com.devangam.client.razorpay;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RazorpayHttpClient {

	// Retrieve a Payment by ID ,
	// This endpoint is used for retrieving a specific payment object by its id.
	public Payment getPaymentById(String paymentId) throws RazorpayException {
		Payment payment = null;
		if (StringUtils.isNotBlank(paymentId)) {
			log.debug("Get RazorPayment start. RazorPaymentPaymentId=" + paymentId);
			try {
				// Initialize client
				RazorpayClient razorpayClient = getRazorPayClient();
				// Fetch a particular payment:
				payment = razorpayClient.Payments.fetch(paymentId);
			} catch (RazorpayException razorpayException) {
				throw new RazorpayException(razorpayException.getMessage() + " RazorPaymentPaymentId=" + paymentId, razorpayException);
			} catch (Exception exception) {
				throw new RazorpayException(exception.getMessage() + " RazorPaymentPaymentId=" + paymentId, exception);
			}
		} else {
			throw new RazorpayException("Razorpay payment id is null or empty");
		}
		return payment;
	}

	private RazorpayClient getRazorPayClient() throws RazorpayException {
		RazorpayClient razorpayClient = null;
		try {
			// TODO : Fetch Razorpay with key_id & key_secret from
			// PaymentSecurityInfo table.
			razorpayClient = new RazorpayClient("rzp_test_z5OTBfUB0Ell1B", "7HDgzB4yvAH5z5");
		} catch (Exception exception) {
			throw new RazorpayException("Initialize RazorpayClient failed.", exception);
		}
		return razorpayClient;
	}

	public List<Payment> getRazorPayments() {
		List<Payment> payments = null;
		try {
			// Initialize client
			RazorpayClient razorpayClient = getRazorPayClient();
			// Fetch all payments
			payments = razorpayClient.Payments.fetchAll();
		} catch (RazorpayException razorpayException) {

		} catch (Exception exception) {

		}
		return payments;
	}

}
