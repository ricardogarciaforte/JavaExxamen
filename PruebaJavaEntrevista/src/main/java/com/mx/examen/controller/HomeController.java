package com.mx.examen.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This Class represents a controller for the purpose of accessing the application's home page.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
@Controller
public class HomeController {

	private static final Logger LOGGER = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String homePage() {
		
		LOGGER.debug("Accessed Home Page!");
		
		return "home";		
	}

}
