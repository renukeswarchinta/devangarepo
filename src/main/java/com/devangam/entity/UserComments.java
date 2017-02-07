package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.devangam.enums.HelpingHandMasterEnum;

@Entity
public class UserComments {
	
	

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		
		@Column(name="comments")
		private String comments;
		@Column(name="helpingHandType")
	    private String helpingHandType;
		@Column(name="user_id")
		private int userId;
		@Column(name="helpingHandId")
		private int helpingHandId;
		
		UserComments(){}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public String getHelpingHandType() {
			return helpingHandType;
		}

		public void setHelpingHandType(String helpingHandType) {
			this.helpingHandType = helpingHandType;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getHelpingHandId() {
			return helpingHandId;
		}

		public void setHelpingHandId(int helpingHandId) {
			this.helpingHandId = helpingHandId;
		}
		
		
		
		
		
	}
