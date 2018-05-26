package com.controller;


import com.google.gson.Gson;
import com.entity.RegistrationRequest;
import com.entity.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.util.UtilityClass;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RegistrationController {

    @Autowired
    UtilityClass util;

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST, produces = { "application/json", "application/xml" })
    public @ResponseBody RegistrationResponse registration(@RequestBody RegistrationRequest request) {
        System.out.println("In Registration Controller");
        System.out.println("Registration Request :: "+request.toString());
        RegistrationResponse response = new RegistrationResponse();
        String otp = util.otpGenerator();
        util.sendSMS(otp,request.getMobileNumber());
        util.sendEmail(request.getEmailAddress(),otp);
        response.setMessage("Send OTP to your registered Mobile");
        System.out.println("Registration Response :: "+response.toString());
        return response;




    }
}
