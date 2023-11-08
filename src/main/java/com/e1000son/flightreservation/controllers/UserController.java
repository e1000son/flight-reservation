package com.e1000son.flightreservation.controllers;

import com.e1000son.flightreservation.entities.User;
import com.e1000son.flightreservation.repos.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private IUserRepository userRepository;
    @RequestMapping("/showReg")
    public String showRegistrationPage(){
        return "login/registerUser";
    }

    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, ModelMap modelMap){
        userRepository.save(user);
        return "/login/login";
    }

}
