package com.example.demo.config;


import com.example.demo.security.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Encoder;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.example.demo.security.providers")
public class SecurityConf  extends WebSecurityConfigurerAdapter {


        final PrincipalDetailsService principalDetailsService;





    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/login","/").permitAll()
                .antMatchers("/successPage").authenticated()
                        .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .failureUrl("/login?error=true")
                .usernameParameter("name")
                        .and()
                .exceptionHandling()
                .accessDeniedPage("/successPage")
                        .and()
                .logout();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/js**");
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(principalDetailsService)
                .passwordEncoder(getEncoder());
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}
