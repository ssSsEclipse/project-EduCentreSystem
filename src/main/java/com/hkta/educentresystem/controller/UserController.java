package com.hkta.educentresystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

}
