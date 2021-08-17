package com.psl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Configuration
	@Order(3)
	public static class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
		
		@Autowired
		AdminUserDetails adminUserDetails;
		
		@Autowired
		RESTAuthenticationEntryPoint authenticationEntryPoint;
		
		public AdminSecurityConfiguration() {
			super();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(adminUserDetails).passwordEncoder(bCryptPasswordEncoder);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			
			http.cors().and().csrf().disable()
			.antMatcher("/managers/**").authorizeRequests()
			.anyRequest().authenticated()
	    	.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	}

	@Configuration
	@Order(2)
	public static class TrainerSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
		
		@Autowired
		TrainerUserDetails trainerUserDetails;
		
		@Autowired
		RESTAuthenticationEntryPoint authenticationEntryPoint;
		
		public TrainerSecurityConfiguration() {
			super();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(trainerUserDetails).passwordEncoder(bCryptPasswordEncoder);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {

			http.cors().and().csrf().disable()
			.antMatcher("/trainers/**").authorizeRequests()
			.anyRequest().authenticated()
	    	.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	}

	@Configuration
	@Order(1)
	public static class LearnerSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
		
		@Autowired
		LearnerUserDetails learnerUserDetails;
		
		@Autowired
		RESTAuthenticationEntryPoint authenticationEntryPoint;
		
		public LearnerSecurityConfiguration() {
			super();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(learnerUserDetails).passwordEncoder(bCryptPasswordEncoder);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {

			http.cors().and().csrf().disable()
			.antMatcher("/learners/**").authorizeRequests()
			.anyRequest().authenticated()
	    	.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	}
	
}
