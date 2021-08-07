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
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
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
			.usersByUsernameQuery("select username, password, enabled from usercredentials where username=?;")
			.authoritiesByUsernameQuery("select username, role from usercredentials where username=?;")
			;
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/learners/{id}/**").access("@userSecurity.hasId(authentication, #id)")
			.antMatchers("/trainers/{id}/**").access("@userSecurity.hasId(authentication, #id)")
			.antMatchers("/managers/{id}/**").access("@userSecurity.hasManagerId(authentication, #id)")
			.antMatchers("/learners/**").access("@userSecurity.hasAccessToLearners(authentication)")
			.antMatchers("/trainers/**").access("@userSecurity.hasAccessToTrainers(authentication)")
			.antMatchers("/managers/**").access("@userSecurity.hasAccessToManagers(authentication)")
			.anyRequest().authenticated()
			.and()
			.formLogin().defaultSuccessUrl("/homepage/").permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/405")
			;
	}	
}


