package com.jwt.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDTO {

	private int id;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Length(min = 3,max = 20)
	private String password;
}
