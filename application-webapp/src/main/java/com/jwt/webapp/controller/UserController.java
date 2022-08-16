package com.jwt.webapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.common.dto.ApiResponseDTO;
import com.jwt.dto.UserDTO;
import com.jwt.service.controller.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid UserDTO dto){
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));		
		return new ResponseEntity<>(new ApiResponseDTO<>(true,service.save(dto)),HttpStatus.CREATED);
	}
	
}
