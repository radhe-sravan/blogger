package com.blogger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeRequests().antMatchers("/post/**").hasRole("USER")
        .antMatchers("/").hasRole("USER").and().formLogin().loginPage("/login");
  }

  @Autowired
  public void configureUsers(AuthenticationManagerBuilder builder) throws Exception {
    builder.inMemoryAuthentication().withUser("sravan").password("12345").roles("USER", "ADMIN")
        .and().withUser("user").password("password").roles("USER");
  }

}
