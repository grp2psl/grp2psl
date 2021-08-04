// package com.psl.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @Order(2)
// @EnableWebSecurity
// public class TrainersSecurityConfig extends WebSecurityConfigurerAdapter {
    
//     @Autowired
// 	private javax.sql.DataSource dataSource;

// 	public PasswordEncoder passwordEncoder(){
// 		return new PasswordEncoderTest();
// 	}

// 	@Autowired
// 	public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
// 		authBuilder.jdbcAuthentication()
// 			.dataSource(dataSource)
// 			.passwordEncoder(passwordEncoder())
// 			.usersByUsernameQuery("select trainerid, password, enabled from trainer where trainerid=?;")
// 			.authoritiesByUsernameQuery("select trainerid, role from trainer where trainerid=?;")
// 			;
// 	}

//     @Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http.authorizeRequests()
// 			.antMatchers("/trainers/{id}/**").access("@userSecurity.hasId(authentication, #id)")
// 			.antMatchers("/managers/**").access("false")
// 			.antMatchers("/learners/**").access("false")
// 			.anyRequest().authenticated()
// 			.and()
// 			.formLogin().defaultSuccessUrl("/trainers/").permitAll()
// 			.and()
// 			.logout().permitAll()
// 			.and()
// 			.exceptionHandling().accessDeniedPage("/405")
// 			;
// 	}	
// }