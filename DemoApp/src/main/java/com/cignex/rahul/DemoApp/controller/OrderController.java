package com.cignex.rahul.DemoApp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cignex.rahul.DemoApp.model.Order;
import com.cignex.rahul.DemoApp.model.Product;
import com.cignex.rahul.DemoApp.service.OrderService;
import com.cignex.rahul.DemoApp.service.UserService;
import com.cignex.rahul.DemoApp.utils.CustomError;
import com.cignex.rahul.DemoApp.utils.DemoAppConstantValues;
import com.cignex.rahul.DemoApp.utils.ResponseBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllOrders() throws JsonParseException, JsonMappingException, IOException, NotFoundException {
		List<Order> orderList = orderService.getAllorder();
		if (orderList.size() < 1) {
			return new ResponseEntity(new CustomError(DemoAppConstantValues.ORDERS_NOT_FOUND, HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
				
		return new ResponseEntity(new ResponseBuilder<>(DemoAppConstantValues.ORDER_LIST, HttpStatus.OK, orderList),
				HttpStatus.ACCEPTED.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getOrder(@PathVariable Long id) {
		Order order = orderService.getOrder(id);

		if (order == null) {
			return new ResponseEntity(new CustomError(DemoAppConstantValues.ORDER_NOT_FOUND, HttpStatus.NOT_FOUND),
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(new ResponseBuilder<>(DemoAppConstantValues.ORDER_FOUND, HttpStatus.OK, order),
				HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
		return new ResponseEntity(new CustomError(orderService.deleteOrder(id), HttpStatus.OK), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteOrder() {
		return new ResponseEntity(new CustomError(orderService.deleteAllOrders(), HttpStatus.OK), HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/")
	public ResponseEntity<?> saveOrder(@PathVariable Long id, @RequestBody List<Product> product)
			throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		Order orderObj = new Order();
		orderObj.setUser(userService.getuser(id));
		orderObj.setProductList(mapper.writeValueAsString(product));
		orderService.saveOrder(orderObj);

		return new ResponseEntity(new ResponseBuilder<>(DemoAppConstantValues.ORDER_ADDED, HttpStatus.OK, orderObj),
				HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/")
	public ResponseEntity<?> editOrder(@RequestBody Order order) {

		Order orderObj = new Order();
		orderObj.setUser(order.getUser());
		orderObj.setId(order.getId());
		orderObj.setProductList(order.toString());

		return new ResponseEntity(
				new ResponseBuilder<>(DemoAppConstantValues.ORDER_ADDED, HttpStatus.OK, orderService.saveOrder(order)),
				HttpStatus.OK);
	}
}