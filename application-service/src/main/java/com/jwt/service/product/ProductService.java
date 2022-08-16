package com.jwt.service.product;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.common.service.ResourceNotFoundException;
import com.jwt.domain.product.Product;
import com.jwt.dto.product.ProductDTO;
import com.jwt.repository.ProductRepository;
import com.jwt.service.GenericCRUDServiceImpl;



@Service
public class ProductService extends GenericCRUDServiceImpl<Product, ProductDTO> {

	@Autowired(required = true)
	private ProductRepository repository;
	

	private ModelMapper modelMapper=new ModelMapper();
	
	@Override
	public Optional<Product> find(ProductDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public ProductDTO mapToDto(Product domain) {
		return modelMapper.map(domain, ProductDTO.class);
	}

	@Override
	public Product mapToDomain(ProductDTO dto) {
		return modelMapper.map(dto, Product.class);
	}
	
	public void delete(ProductDTO dto) {
		Optional<Product> domain=repository.findById(dto.getId());
		if(!domain.isPresent()) {
			throw new ResourceNotFoundException(String.valueOf("no econtrado"));
		}
		repository.delete(domain.get());
	}	
}
