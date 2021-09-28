package com.bird.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 *
 */
public class MemberAuthenticationToken extends UsernamePasswordToken {


    private static final long serialVersionUID = -2564928913725078138L;

    private LoginType type;


    public MemberAuthenticationToken() {
        super();
    }


    public MemberAuthenticationToken(String username, String password, LoginType type, boolean rememberMe, String host) {
        super(username, password, rememberMe,  host);
        this.type = type;
    }
    /**免密登录*/
    public MemberAuthenticationToken(String username) {
        super(username, "", false, null);
        this.type = LoginType.NOPASSWD;
    }

    /**账号密码登录*/
    public MemberAuthenticationToken(String username, String pwd) {
        super(username, pwd, false, null);
        this.type = LoginType.PASSWORD;
    }

    public LoginType getType() {
        return type;
    }


    public void setType(LoginType type) {
        this.type = type;
    }



    }
