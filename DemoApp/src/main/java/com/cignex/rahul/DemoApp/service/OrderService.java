package com.cignex.rahul.DemoApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cignex.rahul.DemoApp.model.Order;
import com.cignex.rahul.DemoApp.repositories.OrderRepository;
import com.cignex.rahul.DemoApp.utils.DemoAppConstantValues;

import javassist.NotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllorder() throws NotFoundException{
		List<Order> orderList =orderRepository.findAll();
//		 if (true) {
//	             throw new NotFoundException("user not found");
//	        }
		return orderList;
	}
	
	public List<Order> getOrderByUser(Long id){
		List<Order> orderList = new ArrayList<>();
		orderList =orderRepository.findByuId(id);
		return orderList;
	}
	
	public Order getOrder(Long id){
		return orderRepository.getOne(id);
	}
	public Order saveOrder(Order order){
		orderRepository.save(order);
		return order;
	}
	public String deleteOrder(Long id){
		orderRepository.deleteById(id);
		return DemoAppConstantValues.ORDER_DELETED;
	}
	public String deleteAllOrders(){
		orderRepository.deleteAll();
		return DemoAppConstantValues.ALL_ORDER_DELETED;
	}
}
