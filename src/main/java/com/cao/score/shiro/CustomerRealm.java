package com.cao.score.shiro;

import com.cao.score.entity.User;
import com.cao.score.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //用户账号密码认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录用户名
        String principal = (String) authenticationToken.getPrincipal();
        if(StringUtils.isNotBlank(principal)){
            User param=new User();
            param.setUserName(principal);
            //获取库中登录用户信息
            User user = userService.queryOne(param);
            if (user!=null) {//校对密码
                return new SimpleAuthenticationInfo(user.getUserName(), user.getUserPwd(), ByteSource.Util.bytes(user.getSalt()), this.getName());
            }
        }
        return null;
    }
}
