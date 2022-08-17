package com.jwt.common.service;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public  ResourceNotFoundException(String object) {
		super(String.format("%s", object));
	}

}
