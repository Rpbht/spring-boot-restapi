package com.cignex.rahul.DemoApp.controller;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cignex.rahul.DemoApp.model.User;
import com.cignex.rahul.DemoApp.service.UserService;
import com.cignex.rahul.DemoApp.utils.CustomError;
import com.cignex.rahul.DemoApp.utils.DemoAppConstantValues;
import com.cignex.rahul.DemoApp.utils.ResponseBuilder;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Get all users
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers() {
		List<User> allUsers = userService.getAllUser();
		if (allUsers.isEmpty()) {
			return new ResponseEntity(new CustomError(DemoAppConstantValues.NO_RECORDS_FOUND, HttpStatus.NO_CONTENT),
					HttpStatus.NO_CONTENT);
		} else {
			ResponseBuilder<List<User>> userList = new ResponseBuilder(DemoAppConstantValues.LIST_OF_ALL_USERS,
					HttpStatus.OK, allUsers);
			return new ResponseEntity(userList, HttpStatus.OK);
		}
	}

	// Get user by id
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) {
		try {
			User user = userService.getuser(id);
			if (user == null) {
				return new ResponseEntity(new CustomError(DemoAppConstantValues.NO_USER_FOUND, HttpStatus.NO_CONTENT),
						HttpStatus.OK);
			} else {
				ResponseBuilder<List<User>> userList = new ResponseBuilder(DemoAppConstantValues.USER_FOUND,
						HttpStatus.OK, user);
				return new ResponseEntity(userList, HttpStatus.OK);
			}

		} catch (Exception e) {
			System.out.println("Exception:  " + e.getMessage());
			return new ResponseEntity(new CustomError(DemoAppConstantValues.NO_USER_FOUND, HttpStatus.NO_CONTENT),
					HttpStatus.OK);
		}
	}
	
	// Get user by email and password

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/{email}/{password}")
	public ResponseEntity<User> getUser(@PathVariable String email, @PathVariable String password) {
		try {
			User user = userService.isVAlid(email, password);
			System.out.println("Userby Id: " + user);
			if (user == null) {
				return new ResponseEntity(new CustomError(DemoAppConstantValues.NO_USER_FOUND, HttpStatus.NO_CONTENT),
						HttpStatus.OK);
			} else {
				ResponseBuilder<List<User>> userList = new ResponseBuilder(DemoAppConstantValues.USER_FOUND,
						HttpStatus.OK, user);
				return new ResponseEntity(userList, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity(new CustomError(DemoAppConstantValues.NO_USER_FOUND, HttpStatus.NO_CONTENT),
					HttpStatus.OK);
		}
	}

	// Add user
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User userObj = userService.addUser(user);
		ResponseBuilder<User> users = new ResponseBuilder(DemoAppConstantValues.USER_ADDED, HttpStatus.OK, userObj);
		return new ResponseEntity(users, HttpStatus.OK);
	}

	// Edit user
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> editUser(@RequestBody User user) {
		User userObj = userService.addUser(user);
		@SuppressWarnings("rawtypes")
		ResponseBuilder<User> users = new ResponseBuilder(DemoAppConstantValues.USER_EDITED, HttpStatus.OK, userObj);
		return new ResponseEntity(users, HttpStatus.OK);
	}

	// Delete user
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return new ResponseEntity(new CustomError(DemoAppConstantValues.USER_DELETED, HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}
}
