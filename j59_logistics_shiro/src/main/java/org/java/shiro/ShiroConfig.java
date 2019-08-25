package org.java.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: 马果
 * @Date: 2019/6/27 14:18
 * @Description:
 * 在该类中，配置：
 *      1、ShiroFilterFactoryBean
 *      2、securityManager
 *      3、Realm
 *      4、CacheManager
 *      5、凭证匹配器
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        System.out.println("######################3");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置，如果当前请求还没有认证，发出什么请求跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl("login");
        shiroFilterFactoryBean.setSuccessUrl("/leftmenu");
        //设置shiro拦截路径的规则，把这些规则放到一个map集合
        Map<String,String> shiroFilterDefinitionMap = new LinkedHashMap<>();
        shiroFilterDefinitionMap.put("/js/**","anon" );
        shiroFilterDefinitionMap.put("/css/**","anon" );
        shiroFilterDefinitionMap.put("/img/**","anon" );
        shiroFilterDefinitionMap.put("login","anon" );
        shiroFilterDefinitionMap.put("/logout","logout" );//退出
       // shiroFilterDefinitionMap.put("/login","anon" );//登陆
        shiroFilterDefinitionMap.put("/**","authc" );//剩余的所有请求，都需要认证访问

        //把规则放到shiroFilterFactoryBean
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //指定安全管理器通过哪一个域（realm）来进行认证，授权
        securityManager.setRealm(authcRealm());
        //指定要使用的缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    @Bean
    public AuthcRealm authcRealm(){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        AuthcRealm realm = new AuthcRealm();
        //指定realm类的加密方式
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * 只有加载 ShiroDialect,shiro的授权方法，才会执行
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }


    /**
     * 缓存管理
     * @return
     * 1、先缓存shiro-ehcache.xml文件导入到java/resources/config文件
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        //创建缓存管理器
        EhCacheManager ehCacheManager = new EhCacheManager();
        //指定缓存管理器的配置文件路径
        ehCacheManager.setCacheManagerConfigFile("classpath:config/shiro-ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 凭证匹配器，用于指定加密方式
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher =new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }


}
