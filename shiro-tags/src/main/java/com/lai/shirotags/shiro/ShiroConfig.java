package com.lai.shirotags.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Created by lxh on 2019/9/24
 * shiro配置类
 */
@Configuration
public class ShiroConfig  {


    /**
     *创建shiroFilterFActoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("sercurityMananger") DefaultWebSecurityManager sercurityMananger){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器sercurityMananger
        factoryBean.setSecurityManager(sercurityMananger);
        //添加shiro内置过滤器
        /**
         * shiro内置过滤器：
         * anon：无需认证就可以访问
         * authc：必须认证才可以访问资源
         * user：如果使用rememeberme的功能可以直接访问
         * perms：需要授权才可以访问
         * roles：资源需要具有该角色才可以访问
         */
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //map.put("/update","anon");
        map.put("/login","anon");
        map.put("/dologin","anon");
        map.put("/static/**", "anon");
        map.put("/register","anon");
        map.put("/unauthorization","anon");
        map.put("/error","anon");
        //map.put("/selectByConditions","anon");
        map.put("/toform","anon");
        //添加授权过滤器 当前授权拦截后，shiro会自动跳转到未授权页面
        map.put("/update","roles[super]"); //该资源只有super角色能访问
        map.put("/update","perms[user:add]");//该资源只有user:add权限能访问
        map.put("/delUser","perms[user:del]");//该资源只有user:del权限能访问
        map.put("/*","authc");

        //修改跳转登陆页面 ，shiro默认是跳转的login.jsp,需要设置成我们要跳转的login.html页面
        factoryBean.setLoginUrl("/login");
        factoryBean.setUnauthorizedUrl("/unauthorization");
        factoryBean.setFilterChainDefinitionMap(map);

        return factoryBean;
    }
    /**
     * 创建DefaultWebSecurytyManager
     */
    @Bean("sercurityMananger")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建reaml对象
     */
    @Bean("userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }


}
