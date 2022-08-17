package com.jwt.service.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.domain.User;
import com.jwt.dto.UserDTO;
import com.jwt.repository.UserRepository;
import com.jwt.service.GenericCRUDServiceImpl;

@Service
public class UserService extends GenericCRUDServiceImpl<User, UserDTO> {
	
	@Autowired
	private UserRepository repository;
	
	private ModelMapper modelMapper=new ModelMapper();

	@Override
	public Optional<User> find(UserDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public UserDTO mapToDto(User domain) {
		return modelMapper.map(domain, UserDTO.class);
	}

	@Override
	public User mapToDomain(UserDTO dto) {
		return modelMapper.map(dto, User.class);
	}
}
