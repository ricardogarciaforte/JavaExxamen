package com.mx.examen.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This Class represents a controller to manage system login operations.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
@Controller
@RequestMapping("auth")
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    /**
     * This method is responsible for opening the login form for the system user.
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loginPage() {

        LOGGER.debug("Getting login page...");

        return "login";
    }

    /**
     * This method manages login attempts on the application by the user.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) boolean error,
                              @RequestParam(value = "logout", required = false) boolean logout, ModelMap model) {

        LOGGER.debug("Loggin in action...");
        if (error) {
            LOGGER.debug("Invalid username and password! " + error);
            model.addAttribute("error", "Invalid username or password!");
            return new ModelAndView("login", model);
        }
        if (logout) {
            LOGGER.debug("Logged out! ");
            model.addAttribute("logout", "Logged out! You've been logged out successfully.");
            return new ModelAndView("home");
        }
        LOGGER.debug("Going to home page");

        return new ModelAndView("redirect:/home");
    }

    /**
     * This method manages attempts to access pages not allowed to the user.
     */
    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {

        LOGGER.debug("Going to access denied page...");

        return new ModelAndView("accessDenied");
    }

}
