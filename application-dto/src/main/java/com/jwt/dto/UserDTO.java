package com.jwt.dto;

import java.util.HashSet;
import java.util.Set;

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
	
	private Set<RolesDTO> roles=new HashSet<RolesDTO>();
	
}
