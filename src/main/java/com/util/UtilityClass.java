package com.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


@Service
public class UtilityClass {
    public String otpGenerator(){
        int randomPin   =(int)(Math.random()*new Date().getTime())/100000;
        String otp  =String.valueOf(randomPin);
        return otp;
    }

    @Async
    public void sendSMS(String msg, String mobileNumber) {
        try {
            // Construct data
            String apiKey = "apikey=" + "bqUJQlO1kIo-Y0cvAT1rrz3fBlP8PtUpb0tntNbFKo";
            String message = "&message=" + "OTP :: "+msg;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + mobileNumber;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            System.out.println("SMS sent successfully :: stringBuffer.toString()");;

        } catch (Exception e) {
            System.out.println("Error SMS "+e);

        }
    }

    @Async
    public void sendEmail(String toAddress, String otp) {

         String username = "";
         String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        props.put("mail.defaultEncoding", "UTF-8");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");



        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            message.setSubject("ONE TIME PASSWORD");
            message.setText("PLEASE FIND THE OTP FOR FURTHER PROCESS ::"+otp);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
