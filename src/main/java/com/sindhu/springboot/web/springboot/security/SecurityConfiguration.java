package com.sindhu.springboot.web.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//This Configuration annotation is used to indicate the class that declares one or more methods and may be 
//processed by the spring container to generate bean definition and service requests for those beans at runtime.
//This is used to create beans that are needed to configure Security arround our application
//To configure the security we extends the class to WebSecurityConfigurerAdapter class.
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	//Create Users - in28Minutes/dummy
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		PasswordEncoder encoder =
			     PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("sindhuKatta").password(encoder.encode("dummy")).roles("USER","ADMIN");

		//auth.inMemoryAuthentication().withUser("in28Minutes").password("{noop}dummy").roles("USER","ADMIN");
		
	}
	
	//Create Login form
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/login","/h2-console/**").permitAll()
				.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
				.formLogin();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
