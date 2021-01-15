package com.ctgu.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author larry
 * @date 2021/1/15 16:06
 */

@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //定制请求授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
            .antMatchers("/level1/**").hasRole("VIP1")
            .antMatchers("/level2/**").hasRole("VIP2")
            .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin")
            .loginProcessingUrl("/login");
        //默认post形式的/login代表处理登录
        //一旦定制后，那么loginpage的post请求就是登录

        //注销用户
        http.logout().logoutSuccessUrl("/");

        //记住我功能，登录成功后将cookie发给浏览器进行保存，点击注销再删除
        http.rememberMe().rememberMeParameter("remember");
    }


    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("lirui").password("1111").roles("VIP1", "VIP2")
            .and()
            .withUser("wang").password("1111").roles("VIP3")
            .and().passwordEncoder(new CustomPasswordEncoder());
    }
}
