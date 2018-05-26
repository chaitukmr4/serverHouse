package com.entity;

public class LoginResponse {
    private String validUserOrNot;

    public String getValidUserOrNot() {
        return validUserOrNot;
    }

    public void setValidUserOrNot(String validUserOrNot) {
        this.validUserOrNot = validUserOrNot;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "validUserOrNot='" + validUserOrNot + '\'' +
                '}';
    }
}
