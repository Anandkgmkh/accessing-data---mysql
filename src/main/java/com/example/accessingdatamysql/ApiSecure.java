package com.example.accessingdatamysql;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ApiSecure extends WebSecurityConfigurerAdapter{
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("anand").password("{noop}mypassword").roles("user").and()
		.withUser("admin").password("{noop}admin").roles("user","admin");
	}

	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic().and().authorizeRequests().antMatchers("/demo/all").hasRole("user")
		.antMatchers("/demo/deleteall").hasRole("admin").and().csrf().disable().headers()
		.frameOptions().disable();
	}
}
