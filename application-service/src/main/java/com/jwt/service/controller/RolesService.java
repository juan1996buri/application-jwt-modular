package com.jwt.service.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.domain.Roles;
import com.jwt.dto.RolesDTO;
import com.jwt.repository.RolesRepository;
import com.jwt.service.GenericCRUDServiceImpl;

@Service
public class RolesService extends GenericCRUDServiceImpl<Roles, RolesDTO> {

	@Autowired
	private RolesRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Roles> find(RolesDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public RolesDTO mapToDto(Roles domain) {
		return modelMapper.map(domain, RolesDTO.class);
	}

	@Override
	public Roles mapToDomain(RolesDTO dto) {
		return modelMapper.map(dto, Roles.class);
	}
}
