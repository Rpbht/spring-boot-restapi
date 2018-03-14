package com.cignex.rahul.DemoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cignex.rahul.DemoApp.model.Product;
import com.cignex.rahul.DemoApp.service.ProductService;
import com.cignex.rahul.DemoApp.utils.CustomError;
import com.cignex.rahul.DemoApp.utils.DemoAppConstantValues;
import com.cignex.rahul.DemoApp.utils.ResponseBuilder;

@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		if (products.size() < 1) {
			return new ResponseEntity(
					new CustomError(DemoAppConstantValues.NO_PRODUCTS_AVAILABLE, HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(new ResponseBuilder(DemoAppConstantValues.LIST_PRODUCTS, HttpStatus.OK, products),
				HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public ResponseEntity<?> getProducts(@PathVariable Long id) {
		Product product = productService.getProduct(id);
		System.out.println(product);
		if (product == null) {
			return new ResponseEntity(new CustomError(DemoAppConstantValues.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(new ResponseBuilder(DemoAppConstantValues.PRODUCT_FOUND, HttpStatus.OK, product),
				HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return new ResponseEntity(new ResponseBuilder<>(DemoAppConstantValues.PRODUCT_SAVED, HttpStatus.OK, product),
				HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> editProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return new ResponseEntity(new ResponseBuilder<>(DemoAppConstantValues.PRODUCT_EDITED, HttpStatus.OK, product),
				HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		return new ResponseEntity(new CustomError(productService.deleteProduct(id), HttpStatus.OK),
				HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProducts() {
		return new ResponseEntity(new CustomError(productService.deleteAllProduct(), HttpStatus.OK),
				HttpStatus.OK);
	}

}
