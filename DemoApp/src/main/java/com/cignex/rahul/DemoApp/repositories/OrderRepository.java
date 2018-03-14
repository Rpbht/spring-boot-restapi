package com.cignex.rahul.DemoApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cignex.rahul.DemoApp.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("select o from Order o where o.user.id =?1")
	List<Order> findByuId(Long u_id);


}
