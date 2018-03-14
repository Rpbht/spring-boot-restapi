package com.cignex.rahul.DemoApp.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "order_tbl")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5359103671909277401L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productList;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "uId")
	private User user;

	public Order() {
		super();
	}

	public Order(Long id, String products, User user) {
		super();
		this.id = id;
		this.productList = products;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProductList() throws JsonParseException, JsonMappingException, IOException {
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		List<Product> productList1 = mapper.readValue(productList,new TypeReference<List<Product>>(){});
		for (int j = 0; j < productList1.size(); j++) {
			Product product = new Product(productList1.get(j).getName(), productList1.get(j).getPrice());	
		}
		
		
		
		
		
		return productList1;
	}

	public void setProductList(String productList) {
		this.productList = productList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", products=" + productList + ", user=" + user + "]";
	}

}
