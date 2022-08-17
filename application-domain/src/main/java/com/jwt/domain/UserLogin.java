package com.jwt.domain;

import lombok.Data;

@Data
public class UserLogin {
	
	private String email;
	
	private String password;
	
	private String token;
}
