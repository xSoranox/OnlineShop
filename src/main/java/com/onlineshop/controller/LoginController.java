package com.onlineshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/connectadmin")
    public ModelAndView openAdminMode() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    
    @RequestMapping("/connectadmin/failed")
    public ModelAndView failedConnection() {
        String message = "Invalid username/password";
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
