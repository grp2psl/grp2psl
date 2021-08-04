package com.psl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
@EnableWebSecurity
public class LearnersSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
	private javax.sql.DataSource dataSource;

	public PasswordEncoder passwordEncoder(){
		return new PasswordEncoderTest();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("select learnerid, password, enabled from learner where learnerid=?;")
			.authoritiesByUsernameQuery("select learnerid, role from learner where learnerid=?;")
			;
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/learners/{id}/**").access("@userSecurity.hasId(authentication, #id)")
			.antMatchers("/trainers/**").access("false")
			.antMatchers("/managers/**").access("false")
			.anyRequest().authenticated()
			.and()
			.formLogin().defaultSuccessUrl("/learners/").permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/405")
			;
	}	
}


