package com.bird.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 *
 */
public class AdminAuthenticationToken extends UsernamePasswordToken {

    private LoginType type;


    public AdminAuthenticationToken(String username, String password) {
        super(username, password);
        this.type = LoginType.PASSWORD;
    }



    public LoginType getType() {
        return type;
    }


    public void setType(LoginType type) {
        this.type = type;
    }



}
