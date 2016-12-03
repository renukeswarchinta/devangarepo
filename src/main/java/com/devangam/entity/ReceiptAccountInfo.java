package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="T_RECEIPT_ACCOUNT_INFO")
@Entity
public class ReceiptAccountInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RECEIPT_ACCOUNTINFO_ID")
	private int receiptAccountInfoId;
	
	@Column(name="HELPING_HAND_ID")
	private int helpingHandId; 
	
	
	
}
