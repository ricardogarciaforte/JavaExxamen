package com.mx.examen.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mx.examen.entity.CurrentUser;

/**
 * Represents the user controller that is logged into the system. 
 * This controller does not receive requests from the pages. 
 * This controller acts as a listener for certain operations on the system.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
@ControllerAdvice
public class CurrentUserController {

    private static final Logger LOGGER = Logger.getLogger(CurrentUserController.class);

    /**
     * Binds with the parameter 'currentUser' and responds if there is a user logged into the system.
     */
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUserAdvice(Authentication authentication) {

        LOGGER.debug("Accessing getCurrentUserAdvice");

        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }

}
