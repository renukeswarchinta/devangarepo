package com.devangam.enums;

public enum HelpingHandMasterEnum {

	EDUCATION(1,"EDUCATION"),OLDAGE_HOME(2,"OLDAGAE_HOMES"),PATIENT(3,"PATIENT");
	
	private String helpingHandName;
	private int helpingHandId;
	HelpingHandMasterEnum(int helpingHandId,String helpingHandName){
		this.helpingHandName = helpingHandName;
		this.helpingHandId = helpingHandId;
	}
	
	public String getHelpingHandName(){
		return this.helpingHandName;
	}
	public int getHelpingHandId(){
		return this.helpingHandId;
	}
	
}
