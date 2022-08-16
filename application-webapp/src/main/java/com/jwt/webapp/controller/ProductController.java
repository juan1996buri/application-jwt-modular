package com.jwt.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.common.dto.ApiResponseDTO;
import com.jwt.dto.product.ProductDTO;
import com.jwt.service.product.ProductService;



@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody ProductDTO dto) {				
		return new ResponseEntity<>(new ApiResponseDTO<>(true,service.save(dto) ), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Object> findAll() {
		List<ProductDTO> list = service.findAll(new ProductDTO());
		if (!list.isEmpty()) {
			ApiResponseDTO<List<ProductDTO>> response = new ApiResponseDTO<>(true, list);
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}
}
