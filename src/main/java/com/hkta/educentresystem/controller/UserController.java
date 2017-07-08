package com.hkta.educentresystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hkta.educentresystem.dto.ResponseMessage;
import com.hkta.educentresystem.entity.User;
import com.hkta.educentresystem.exception.ResourceNotFoundException;
import com.hkta.educentresystem.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String VIEW_USERS = "users";

	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName(VIEW_USERS);
		return model;
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	@ResponseBody
	public Page<User> listUsers(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		Page<User> resultPage = userService.findAllPaged(page, size);
		if (page > resultPage.getTotalPages()) {
			throw new ResourceNotFoundException();
		}
		return resultPage;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		return user;
	}
	
	@RequestMapping(method = RequestMethod.PATCH )
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateUser(@RequestBody User user) {
		User updatedUser = userService.save(user);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(updatedUser.getId(), User.class.getName(), "User updated!"));
		}
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessage("Unable to update user"));
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE )
	@ResponseBody
	public ResponseEntity<ResponseMessage> deletePerson(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		if (user != null) {
			userService.delete(user);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(id, User.class.getName(), "User deleted!"));
		}
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessage("Unable to delete user"));
	} 

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> addUser(@RequestBody User user) {
		User existingUser = userService.findByUsername(user.getUsername());
		if (existingUser != null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseMessage("Username already exists"));
		}
		User newUser = userService.save(user);
		if (newUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessage("Unable to add user"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(newUser.getId(), User.class.getName(),  "User created!"));
	}

}
