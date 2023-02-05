package com.youlianmei.config;

import com.youlianmei.filter.CORSAuthenticationFilter;
import com.youlianmei.model.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

    //此处用于实现授权功能，配置需要拦截的接口
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        log.info("进入拦截校验页面");
        //拦截页面
        Map<String, String> filterMap = new LinkedHashMap<>();
        //登录/登出，所有人的权限
        filterMap.put("/login", "anon");
        filterMap.put("/logout", "anon");
        filterMap.put("/ces01", "anon");
        //filterMap.put("/**","authc");
        filterMap.put("/**", "corsAuthenticationFilter");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //未登录页面跳转
        //shiroFilterFactoryBean.setLoginUrl("/show");
        //未有权限页面跳转
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        //自定义过滤器
        Map<String, Filter> corsfilterMap = new LinkedHashMap<>();
        corsfilterMap.put("corsAuthenticationFilter", new CORSAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(corsfilterMap);
        log.info("---shiroFilterFactoryBean--");
        return shiroFilterFactoryBean;
    }

    //注入对应的userRealm类
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
        SecurityManager.setRealm(userRealm);
        return SecurityManager;
    }
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        //注册MD5加密
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * 设置shiro加密方式
     *
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用md5 算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数： 意为加密几次
        hashedCredentialsMatcher.setHashIterations(2);

        return hashedCredentialsMatcher;
    }

}