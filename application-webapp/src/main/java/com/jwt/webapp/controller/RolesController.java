package com.jwt.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.common.dto.ApiResponseDTO;
import com.jwt.dto.RolesDTO;
import com.jwt.service.controller.RolesService;

@RestController
@RequestMapping(value = "/roles")
public class RolesController {
	
	@Autowired
	private RolesService service;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody RolesDTO dto) {			
		return new ResponseEntity<>(new ApiResponseDTO<>(true,service.save(dto) ), HttpStatus.CREATED);
	}
}
