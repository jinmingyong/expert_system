package com.jin.expertsystem.expertsystem.base.security;

import com.jin.expertsystem.expertsystem.base.jwt.JwtAuthenticationTokenFilter;
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
 * @author GaoLiwei
 * @date 2019/4/15
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ResourceDao resourceDao;


    /**
     * 装载BCrypt密码编码器
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置用户数据来源
     *
     * @author GaoLiWei
     * @date 16:06 2019/4/15
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置访问接口需要的权限
     * @return MyFilterInvocationSecurityMetadataSource
     */
    @Bean
    public MyFilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource();

        //将不需要权限的路径放到map中，并设置权限为permitAll
        //swagger不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/swagger-ui.html/**","permitAll");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/swagger-resources/**","permitAll");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/webjars/**","permitAll");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/v2/**","permitAll");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/csrf","permitAll");
        //登陆不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/loginController/login","permitAll");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/loginController/register","permitAll");
        //查询菜单不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/loginController/queryMenuList","permitAll");
        //根据token获取用户信息
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/loginController/getUserInfoByToken","permitAll");
        //druid连接池监控不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/druid/**","permitAll");
        //静态资源不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/*.html","permitAll");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/static/js/**","permitAll");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/favicon.ico","permitAll");
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/","permitAll");
        //代码生成不需要权限
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/codeGenerator/**","permitAll");
        //更新接口权限的接口不需要权限（正式环境需要权限，开发测试不需要）
        MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/menuResourceManagementController/updateUrlPer","permitAll");

        //所有接口都不需要权限
        //MyFilterInvocationSecurityMetadataSource.urlPerMap.put("/**/**","permitAll");

        //配置访问路径权限
        List<PathPermission> pathPermissionList = resourceDao.listPathPermission();
        SecurityUtil.setUrlPer(pathPermissionList);

        return securityMetadataSource;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new MyAccessDecisionVoter());
        return new UnanimousBased(decisionVoters);
    }

    /**
     * 设置请求授权
     *
     * @author GaoLiWei
     * @date 16:27 2019/4/15
     **/
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

//		 添加JWT filter
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //开启请求权限配置
                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 允许对于网站静态资源，无需任何权限，可以任意访问
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
                //登陆不需要权限
//                .antMatchers("/user/login").permitAll()
                //druid连接池监控不需要权限
//                .antMatchers("/druid/**").permitAll()
                //swagger不需要权限
//                .antMatchers("/swagger-ui.html/**").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/v2/**").permitAll()
                //设置某些需要特定权限或者角色才可以访问的内容
                //使用.hasRole()的时候，会自动加上前缀ROLE_,就是说：如果数据库中的角色是ROLE_ADMIN,则设置的时候只需要写ADMIN
//                .antMatchers("/myTest/testRoleAdmin").hasRole("ADMIN")
//                .antMatchers("/myTest/testRoleUser").hasRole("USER")
//                .antMatchers("/myTest/testGet").hasAuthority("GET")
//                .antMatchers("/myTest/testDelete").hasAuthority("DELETE")
//				.antMatchers("/**/**").permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setSecurityMetadataSource(mySecurityMetadataSource());
                        return fsi;
                    }
                })
//                 自定义accessDecisionManager
                .accessDecisionManager(accessDecisionManager())
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().fullyAuthenticated()
                .and()
                .exceptionHandling()
                //设置验证失败处理类
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                //设置权限不足处理类
                .accessDeniedHandler(new RestAuthenticationAccessDeniedHandler());
        // 添加自定义JWT filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
