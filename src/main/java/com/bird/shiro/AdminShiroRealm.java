package com.bird.shiro;

import com.bird.config.AdminAuthenticationToken;
import com.bird.config.MemberAuthenticationToken;
import com.bird.entity.Admin;
import com.bird.entity.Member;
import com.bird.service.AdminService;
import com.bird.service.MemberService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class AdminShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取登录用户名
        String userName= (String) principalCollection.getPrimaryPrincipal();

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println(authenticationToken.getClass());

        System.out.println("member"+(authenticationToken instanceof MemberAuthenticationToken));
        System.out.println(authenticationToken instanceof AdminAuthenticationToken);


//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String username = token.getUsername();
//        Admin admin = adminService.selectByUserName(username);
//
//        if(admin == null)
//        {
//            return null;
//        }
//
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                admin.getUsername(), //用户名
//                admin.getPassword(), //密码
//                ByteSource.Util.bytes(admin.getUsername()),//salt=username+salt
//                getName()  //realm name
//        );
//
//        return authenticationInfo;
//

        if(authenticationToken instanceof MemberAuthenticationToken) //普通用户的认证逻辑
        {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            Map query_map = new HashMap<>();
            String openId =token.getPrincipal().toString();
            query_map.put("openId",openId);
            Member member = memberService.getMember(query_map);
            if(member == null)
            {
                return null;
            }

            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    member, //用户
                    member.getPassword(), //密码
                    ByteSource.Util.bytes(member.getOpenId()),//salt=username+salt
                    getName()  //realm name
            );

            return authenticationInfo;
        }
        else if(authenticationToken instanceof AdminAuthenticationToken) //管理员的认证逻辑
        {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            String username = token.getUsername();
            Admin admin = adminService.selectByUserName(username);

            if(admin == null)
            {
                return null;
            }

            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    admin.getUsername(), //用户名
                    admin.getPassword(), //密码
                    ByteSource.Util.bytes(admin.getUsername()),//salt=username+salt
                    getName()  //realm name
            );

            return authenticationInfo;
        }
        else
        {
            return null;
        }
    }
}
