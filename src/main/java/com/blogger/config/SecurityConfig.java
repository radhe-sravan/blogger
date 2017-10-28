package com.blogger.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeRequests().antMatchers("/post/**").hasAnyRole("USER","ADMIN")
        .antMatchers("/").hasAnyRole("USER","ADMIN").and().formLogin().loginPage("/login");
  }

  @Autowired
  public void configureUsers(AuthenticationManagerBuilder builder) throws Exception {
    builder.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username,password,enabled from blog_user where username=?")
        .authoritiesByUsernameQuery("select username,role from blog_user where username=?");
  }

}
