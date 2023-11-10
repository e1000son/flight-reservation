package com.e1000son.flightreservation.controllers;

import com.e1000son.flightreservation.entities.User;
import com.e1000son.flightreservation.repos.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private IUserRepository userRepository;
    @RequestMapping("/showReg")
    public String showRegistrationPage(){
        return "login/registerUser";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, ModelMap modelMap){
        userRepository.save(user);
        return "/login/login";
    }

    @RequestMapping("/showLogin")
    public String showLoginPage(){
        return "/login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
    ModelMap modelMap){
        User user = userRepository.findByEmail(email);
        if (user.getPassword().equals(password)){
            return "findFlights";
        } else {
            modelMap.addAttribute("msg", "Invalid username or password. Please, try again.");
        }
        return "/login/login";
    }

}
