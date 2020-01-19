package org.potholes.security;

import org.potholes.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private FuncFilterInvocationSecurityMetadataSource funcFilterInvocationSecurityMetadataSource;
    @Autowired
    private FuncAccessDecisionManager funcAccessDecisionManager;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyInvalidSessionStrategy myInvalidSessionStrategy;
    @Autowired
    private MyExpiredSessionStrategy myExpiredSessionStrategy;
    @Autowired
    private SessionRegistry sessionRegistry;

    /***
     * 登陆验证账号密码
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /***
     * 允许加载静态文件
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/checkLogin", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 权限
        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                // 加载权限数据
                o.setSecurityMetadataSource(funcFilterInvocationSecurityMetadataSource);
                // 权限决策/授权
                o.setAccessDecisionManager(funcAccessDecisionManager);
                return o;
            }
        });
        // 登录
        http.formLogin().loginPage("/checkLogin");
        // 支持JSON方式登录
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // session 失效
        http.sessionManagement().invalidSessionStrategy(myInvalidSessionStrategy);
        // SESSION 过期(实现单用户单次登录) Session 共享之后会失效,需要重写sessionRegistry
        // https://www.cnblogs.com/sweetchildomine/p/7007242.html
        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry)
                .expiredSessionStrategy(myExpiredSessionStrategy);
        // 注销
        http.logout().logoutUrl("/logout").clearAuthentication(true).permitAll()
                .logoutSuccessHandler(myLogoutSuccessHandler);
        // 拒绝处理
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
        // 取消跨站请求伪造防护
        http.cors().and().csrf().disable();

    }

    @Bean
    public SessionRegistry getSessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Bean
    MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
