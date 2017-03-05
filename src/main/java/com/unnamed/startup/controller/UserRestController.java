package com.unnamed.startup.controller;

import com.unnamed.startup.dto.RegisterUserCommand;
import com.unnamed.startup.exception.BusinessException;
import com.unnamed.startup.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/user")
public class UserRestController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    public UserRestController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUser(@RequestBody RegisterUserCommand registerUserCommand) throws BusinessException {
        userRegistrationService.registerUser(registerUserCommand);
    }
}
