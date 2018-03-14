package com.cignex.rahul.DemoApp.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cignex.rahul.DemoApp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
//	@Query(value = "from User u where u.uEmail=:uEmail AND u.uPassword=:uPassword")
//	User isValid(@Param("uEmail") String uEmail, @Param("uPassword") String uPassword);
	
	@Query(value = "from User where uEmail=:#{#user.uEmail} AND uPassword=:#{#user.uPassword}")
	User isValid(@Param("user") User user);
}
