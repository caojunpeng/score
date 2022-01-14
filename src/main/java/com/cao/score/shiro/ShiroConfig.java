package com.cao.score.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilter过滤所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给ShiroFilter配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<String, String>();
        //authc：拦截
        map.put("/*","authc");
        //anon：不拦截（资源文件）
        map.put("*.img", "anon");
        map.put("*.png", "anon");
        map.put("/css/**", "anon");
        map.put("/bootstrap/**", "anon");
        map.put("/datatable/**", "anon");
        map.put("/zTree/**", "anon");
        map.put("/images/**", "anon");
        map.put("/static/**", "anon");
        map.put("/jquery/**", "anon");
        map.put("/js/**", "anon");
        map.put("/layer/**", "anon");
        map.put("/layui/**", "anon");
        map.put("/loginHtml**", "anon");
        map.put("/saveUserPage", "anon");
        map.put("/saveUser", "anon");
        // 设置认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
        return securityManager;
    }
    //创建自定义Realm
    @Bean
    public Realm getRealm() {
        CustomerRealm realm = new CustomerRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置使用MD5加密算法
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }
}
