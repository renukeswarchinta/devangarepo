package com.devangam.utils;

public enum HelpingHandType {

	//PATIENT("PATIENT"),OLDAGEHOME("OLDAGEHOME"),EDUCATION("EDUCATION");
	EDUCATION("t_education"),PATIENT("t_patient"),OLDAGEHOME("t_oldage_home");
	
	private String helpingHandType;
	
	HelpingHandType(String helpingHandType){
		this.helpingHandType = helpingHandType;
	}
	public String getHelpingHandType(){
		return this.name();
	}
	
}
