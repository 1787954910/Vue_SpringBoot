package com.example.springboot2test.config;

import com.example.springboot2test.service.AdminServiceImpl;
import com.example.springboot2test.service.StudentServiceImpl;
import com.example.springboot2test.service.TeacherServiceImpl;
import com.example.springboot2test.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/verifyCode");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //关闭csrf防护
        http.csrf().disable();

        //解决跨域问题，cors预检请求放行
//        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        //授权
        http.authorizeRequests()
                .antMatchers("/login").permitAll()//放行/login，不需要认证
                .antMatchers("/").permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");  // 处理异常，拒绝访问就重定向到 403 页面

        http.headers().frameOptions().disable();
    }

}
