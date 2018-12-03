package br.com.devmedia.pocket.controller;

import br.com.devmedia.pocket.entity.CurrentUser;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserController {

    private static final Logger LOGGER = Logger.getLogger(CurrentUserController.class);

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUserAdvice(Authentication authentication) {

        LOGGER.debug("Accessing getCurrentUserAdvice");

        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }
}
