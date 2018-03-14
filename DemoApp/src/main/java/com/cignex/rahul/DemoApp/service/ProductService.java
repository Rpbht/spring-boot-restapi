package com.cignex.rahul.DemoApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cignex.rahul.DemoApp.model.Product;
import com.cignex.rahul.DemoApp.repositories.ProductRepository;
import com.cignex.rahul.DemoApp.utils.DemoAppConstantValues;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product getProduct(Long id){
		return productRepository.getOne(id);
	}
	
	public Product saveProduct(Product product){
		return productRepository.save(product);
	}
	
	public Product editProduct(Product product){
		return productRepository.save(product);
	}
	
	public String deleteProduct(Long id){
		productRepository.deleteById(id);
		return DemoAppConstantValues.PRODUCT_DELETED;
	}
	
	public String deleteAllProduct(){
		productRepository.deleteAll();
		return DemoAppConstantValues.ALL_PRODUCT_DELETED; 
	}
}
