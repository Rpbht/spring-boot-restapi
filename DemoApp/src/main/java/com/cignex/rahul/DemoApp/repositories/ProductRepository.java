package com.cignex.rahul.DemoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cignex.rahul.DemoApp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
