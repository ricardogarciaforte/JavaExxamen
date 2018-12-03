package br.com.devmedia.pocket.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger LOGGER = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String homePage() {
		
		LOGGER.debug("Accessed Home Page!");
		
		return "home";		
	}
}
