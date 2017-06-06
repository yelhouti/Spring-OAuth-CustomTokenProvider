package com.elhouti.oauth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		  auth.inMemoryAuthentication().withUser("username").password("password").roles("ADMIN");
	}
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable().authorizeRequests()
		.antMatchers("/**").fullyAuthenticated().and().httpBasic()
//		.antMatchers("/**").access("#oauth2.hasScope('read')")
		;
		// @formatter:on
	}
}
