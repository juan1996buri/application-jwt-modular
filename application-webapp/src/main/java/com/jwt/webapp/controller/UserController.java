package com.jwt.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.common.dto.ApiResponseDTO;
import com.jwt.domain.User;
import com.jwt.domain.UserLogin;
import com.jwt.dto.UserDTO;
import com.jwt.service.controller.UserService;
import com.jwt.webapp.security.JwtTokenUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwUtil;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid UserDTO dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.save(dto)), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Object> respuesta() {
		List<UserDTO> optional = service.findAll(new UserDTO());
		if (!optional.isEmpty()) {
			return new ResponseEntity<>(new ApiResponseDTO<>(true, optional), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(new ApiResponseDTO<>(true, optional), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserLogin userLogin) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
			User user = (User) authentication.getPrincipal();
			String token = jwUtil.generateAccessToken(user);
			userLogin.setPassword("");
			userLogin.setToken(token);
			return new ResponseEntity<>(new ApiResponseDTO<>(true, userLogin), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping(value = "/username")
	public ResponseEntity<Object> currentUserName() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			final String currentPrincipalName = authentication.getName();
			System.out.println("Authentication: " + authentication);
			System.out.println("Principal: " + authentication.getPrincipal());
			return new ResponseEntity<>(new ApiResponseDTO<>(true, currentPrincipalName), HttpStatus.OK);
		}

		return new ResponseEntity<>(new ApiResponseDTO<>(true, false), HttpStatus.NOT_FOUND);
	}
}
