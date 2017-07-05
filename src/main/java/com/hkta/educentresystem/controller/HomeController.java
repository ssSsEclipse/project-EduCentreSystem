package com.hkta.educentresystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static String VIEW_HOME = "home";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getHome() {
		ModelAndView model = new ModelAndView();
		model.setViewName(VIEW_HOME);
		return model;
	}
}
