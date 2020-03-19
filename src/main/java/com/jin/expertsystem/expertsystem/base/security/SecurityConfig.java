package com.jin.expertsystem.expertsystem.base.security;

import com.jin.expertsystem.expertsystem.base.jwt.JwtAuthenticationTokenFilter;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonResourcesDao;
import com.jin.expertsystem.expertsystem.business.common.dao.CommonRoleUserDao;
import com.jin.expertsystem.expertsystem.business.sysmanage.dao.ResourceDao;
import com.jin.expertsystem.expertsystem.business.sysmanage.model.PathPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author jinmingyong
 * @date 2020/3/14 21:07
 */
@Configuration
@EnableWebSecurity//开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//post请求可用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private ResourceDao resourceDao;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 自定义jwt过滤器
     *
     * @return
     */
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }


    //设置密码加密
    @Bean
    public PasswordEncoder passwordEncoderBean(){
        return new BCryptPasswordEncoder();
    }

    //获得权限资源，当前访问的url需要的具体权限
    @Bean
    public MyFilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource();
        //将不需要权限的路径放到map中，并设置权限为permitAll
        //swagger不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/swagger-ui.html/**","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/swagger-resources/**","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/webjars/**","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/v2/**","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/csrf","ROLE_ANONYMOUS");
        //登陆不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/loginController/login","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/loginController/register","ROLE_ANONYMOUS");
        //查询菜单不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/loginController/queryMenuList","ROLE_ANONYMOUS");
        //根据token获取用户信息
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/loginController/getUserInfoByToken","ROLE_ANONYMOUS");
        //druid连接池监控不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/druid/**","ROLE_ANONYMOUS");
        //静态资源不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/*.html","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/static/js/**","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/favicon.ico","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/","ROLE_ANONYMOUS");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/pic/**","ROLE_ANONYMOUS");
        //代码生成不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/codeGenerator/**","ROLE_ANONYMOUS");
        //更新接口权限的接口不需要权限（正式环境需要权限，开发测试不需要）
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/menuResourceManagementController/updateUrlPer","ROLE_ANONYMOUS");

        //所有接口都不需要权限
        //MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/**/**","ROLE_ANONYMOUS");

        //配置访问路径权限
        List<PathPermission> pathPermissionList = resourceDao.listPathPermission();
            for (PathPermission p:pathPermissionList
            ) {
                MyFilterInvocationSecurityMetadataSource.urlPerMap.put(p.getResourceUrl(),p.getPermissionCode());
            }
        return securityMetadataSource;
    }

    //决策当前的访问是否能通过权限验证
    @Bean
    public MyAccessDecisionManager accessDecisionManager() {
        return new MyAccessDecisionManager();
    }

    //用户信息来源，Spring Security 默认会使用 DaoAuthenticationProvider。
    // DaoAuthenticationProvider 在进行认证的时候需要一个 UserDetailsService 来获取用户的信息 UserDetails，其中包括用户名、密码和所拥有的权限等。
    // 所以如果我们需要改变认证的方式，我们可以实现自己的 AuthenticationProvider；
    // 如果需要改变认证的用户信息来源，我们可以实现 UserDetailsService。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderBean());
    }

    //拦截的配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())//设置验证失败处理类
                .accessDeniedHandler(new RestAuthenticationAccessDeniedHandler())//设置权限不足处理类
                .and()
                //在创建默认的FilterSecurityInterceptor的时候把我们的accessDecisionManager和securityMetadataSource设置进去
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setSecurityMetadataSource(mySecurityMetadataSource());
                        fsi.setAccessDecisionManager(accessDecisionManager());
                        return fsi;
                    }
                }) // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().fullyAuthenticated()
                .and()
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 添加自定义JWT filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        http.headers().cacheControl();
    }
}
