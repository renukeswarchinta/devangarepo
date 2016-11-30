package com.devangam.entity;

public enum AuthorityName {
	ROLE_USER(1,"Role User"), ROLE_ADMIN(2,"Role Admin");
	
	private int id;
	private String roleName;
    private AuthorityName(int id,String roleName){
    	this.id = id;
    }
    public int getRole(){
    	return id;
    }
    public String getRoleName(){
    	return roleName;
    }
}
