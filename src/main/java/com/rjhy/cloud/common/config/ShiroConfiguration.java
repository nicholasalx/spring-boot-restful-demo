package com.rjhy.cloud.common.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by oukingtim
 */
@Configuration
public class ShiroConfiguration {

	@Autowired
    private Environment env;
	
    @Bean  
    public EhCacheManager getEhCacheManager(){  
        EhCacheManager ehcacheManager = new EhCacheManager();  
        ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
        return ehcacheManager;  
    } 
    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }
/*    @Bean(name = "sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;
    }

    */
    /**
     * 配置shiro redisManager
     * @return
     */
/*    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(env.getProperty("spring.redis.host"));
        redisManager.setPort(Integer.valueOf(env.getProperty("spring.redis.port")));
        redisManager.setExpire(1800);//配置过期时间--30分钟
        redisManager.setTimeout(Integer.valueOf(env.getProperty("spring.redis.timeout")));
        // redisManager.setPassword(password);
        return redisManager;
    }*/
    /**
     * cacheManager 缓存 redis实现
     * @return
     */
/*    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }*/
    
    @Bean(name = "securityManager")
    public SecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        //securityManager.setSessionManager(SessionManager());
        //securityManager.setSessionManager(redisCacheManager());
        //用户授权/认证信息Cache, 采用EhCache 缓存
        securityManager.setCacheManager(getEhCacheManager());
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        // 指定 SubjectFactory
        securityManager.setSubjectFactory(new CasSubjectFactory());
        return securityManager;
    }
    
    /**
     * CAS过滤器
     *
     * @return
     * @author SHANHY
     * @create  2016年1月17日
     */
    @Bean(name = "casFilter")
    public CasFilter getCasFilter() {
        CasFilter casFilter = new CasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
        // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
        casFilter.setFailureUrl("/index.html");// 我们选择认证失败后再打开登录页面
        return casFilter;
    }
    
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager, CasFilter casFilter) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/index.html"); //login.jsp //单页应用，如果未登录，跳转到前端MVC入口
        shiroFilter.setUnauthorizedUrl("/index.html"); //403.html  //单页应用，这个不起作用

      //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        // 添加casFilter到shiroFilter中
        filtersMap.put("casFilter", casFilter);
        shiroFilter.setFilters(filtersMap);

        Map<String, String> filterMap = new LinkedHashMap<>();
        //filterMap.put("/api/**", "anon");
        filterMap.put("/oauth/login", "anon");
        filterMap.put("/sysUser/**", "anon");
        /*swagger 相关*/
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");
        /*druid*/
        filterMap.put("/druid/**", "anon");
        /*其它*/
        /*filterMap.put("/assets/**", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/maps/**", "anon");
        filterMap.put("/scripts/**", "anon");
        filterMap.put("/styles/**", "anon");
        filterMap.put("/auth.html", "anon");
        filterMap.put("/index.html", "anon");*/
        filterMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }
    
    /**
     * 限制同一账号登录同时登录人数控制
     * @return
     */
    public KickoutSessionControlFilter kickoutSessionControlFilter(){
       KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
       //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
       //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
       //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
       kickoutSessionControlFilter.setCacheManager(getEhCacheManager());
       //用于根据会话ID，获取会话进行踢出操作的；
       //kickoutSessionControlFilter.setSessionManager(sessionManager());
       //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
       kickoutSessionControlFilter.setKickoutAfter(false);
       //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
       kickoutSessionControlFilter.setMaxSession(1);
       //被踢出后重定向到的地址；
       //kickoutSessionControlFilter.setKickoutUrl("/kickout");
        return kickoutSessionControlFilter;
     }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
