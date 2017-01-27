package com.devangam.utils;

public enum HelpingHandType {

	PATIENT("PATIENT"),OLDAGEHOME("OLDAGEHOME"),EDUCATION("EDUCATION");
	
	private String helpingHandType;
	
	HelpingHandType(String helpingHandType){
		this.helpingHandType = helpingHandType;
	}
	public String getHelpingHandType(){
		return this.name();
	}
	
}
