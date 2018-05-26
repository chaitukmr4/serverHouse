package com.controller;


import com.entity.LoginRequest;

import com.entity.LoginResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {


    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.POST, produces = { "application/json", "application/xml" })
    public @ResponseBody LoginResponse login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();
        System.out.println("In Login Controller ::");
        System.out.println("Login Request :: "+request);

        if("viswa".equalsIgnoreCase(request.getUserName()) && "valid".equalsIgnoreCase(request.getPassword()))
        {
            response.setValidUserOrNot("Valid User");
        }else{
            response.setValidUserOrNot("Not a valid User");
        }
        System.out.println("Login Controller Response :: "+response.toString());
        return response;
    }

}