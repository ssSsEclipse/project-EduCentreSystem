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

import com.hkta.educentresystem.dto.ResponseMessage;
import com.hkta.educentresystem.dto.UserDto;
import com.hkta.educentresystem.entity.User;
import com.hkta.educentresystem.exception.ResourceNotFoundException;
import com.hkta.educentresystem.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

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
	public ResponseEntity<ResponseMessage> updateUser(@RequestBody UserDto userDto) {
		User existingUser = userService.findByUsername(userDto.getUsername());
		if (existingUser != null && !userDto.getPassword().equals(existingUser.getPassword())) {
			userDto.setPassword(userService.encryptPassword(userDto.getPassword()));
		}
		
		User updatedUser = userService.saveDto(userDto);
		if (updatedUser != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(updatedUser.getId(), User.class.getSimpleName(), "views.user.response.message.success.update"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("views.user.response.message.error.update"));
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE )
	@ResponseBody
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		if (user != null) {
			userService.delete(user);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(id, User.class.getSimpleName(), "views.user.response.message.success.delete"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("views.user.response.message.error.delete"));
	} 

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> addUser(@RequestBody UserDto userDto) {
		User existingUser = userService.findByUsername(userDto.getUsername());
		if (existingUser != null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseMessage("views.user.response.message.error.user.already.exists"));
		}
		
		userDto.setPassword(userService.encryptPassword(userDto.getPassword()));
		
		User newUser = userService.saveDto(userDto);
		if (newUser == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("views.user.response.message.error.create"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(newUser.getId(), User.class.getSimpleName(),  "views.user.response.message.success.create"));
	}

}
