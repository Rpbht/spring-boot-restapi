package com.cignex.rahul.DemoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cignex.rahul.DemoApp.model.Order;
import com.cignex.rahul.DemoApp.model.User;
import com.cignex.rahul.DemoApp.service.OrderService;
import com.cignex.rahul.DemoApp.service.UserService;
import com.cignex.rahul.DemoApp.utils.CustomError;
import com.cignex.rahul.DemoApp.utils.DemoAppConstantValues;
import com.cignex.rahul.DemoApp.utils.ResponseBuilder;

@RestController
@RequestMapping("/api/{u_id}/orders")
public class OrderHistoryController {

	@Autowired
	private OrderService orderService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getOrderByUser(@PathVariable("u_id") String u_id) {
		List<Order> oList = orderService.getOrderByUser(Long.parseLong(u_id));
		if (oList.size()<1) {
			return new ResponseEntity(new CustomError(DemoAppConstantValues.ORDER_NOT_FOUND, HttpStatus.NOT_FOUND),
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(new ResponseBuilder<>(DemoAppConstantValues.ORDER_FOUND, HttpStatus.OK, oList),
				HttpStatus.OK);
	}

}
