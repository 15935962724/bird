package com.bird.shiro;

import com.bird.config.AdminAuthenticationToken;
import com.bird.config.LoginType;
import com.bird.config.MemberAuthenticationToken;
import com.bird.entity.Member;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class MyRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {


    private Cache<String, AtomicInteger> passwordRetryCache;

    public MyRetryLimitCredentialsMatcher(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    public MyRetryLimitCredentialsMatcher() {

    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //增加免密登录功能，使用自定义token




        if(token instanceof MemberAuthenticationToken) //普通用户的认证逻辑
        {
            MemberAuthenticationToken usertoken = (MemberAuthenticationToken) token;
            if(usertoken.getType().equals(LoginType.NOPASSWD)){
                return true;
            }
        }
        else if(token instanceof AdminAuthenticationToken) //管理员的认证逻辑
        {
            AdminAuthenticationToken usertoken = (AdminAuthenticationToken) token;
            if(usertoken.getType().equals(LoginType.NOPASSWD)){
                return true;
            }
        }




        boolean matches = super.doCredentialsMatch(token, info);
        return matches;
    }


}
