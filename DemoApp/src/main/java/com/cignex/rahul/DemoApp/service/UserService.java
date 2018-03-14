package com.cignex.rahul.DemoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cignex.rahul.DemoApp.model.User;
import com.cignex.rahul.DemoApp.repositories.UserRepository;
import com.cignex.rahul.DemoApp.utils.DemoAppConstantValues;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> getAllUser(){
		
		return repository.findAll();
	}
	
	public User getuser(Long id){
		return repository.getOne(id);
	}
	
	public User addUser(User user){
		repository.save(user);
		return user;
	}
	
	public User updateUser(User user){
		repository.save(user);
		return user;
	}
	
	public String deleteUser(Long id){
		repository.deleteById(id);
		return DemoAppConstantValues.DELETED_SUCCESSFULLY;
	}
	
	public String deleteAllUser(){
		repository.deleteAll();
		return DemoAppConstantValues.DELETED_All_SUCCESSFULLY;
	}
	
	public User isVAlid(String email, String password){
		
		User user = new User();
		user.setuEmail(email);
		user.setuPassword(password);
		System.out.println("isVAlid");
		return repository.isValid(user);
	}
}
