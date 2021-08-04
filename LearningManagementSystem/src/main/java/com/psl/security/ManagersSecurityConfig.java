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
// @Order(3)
// @EnableWebSecurity
// public class ManagersSecurityConfig extends WebSecurityConfigurerAdapter {
    
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
// 			.usersByUsernameQuery("select managerid, password, enabled from manager where managerid=?;")
// 			.authoritiesByUsernameQuery("select managerid, role from manager where managerid=?;")
// 			;
// 	}

//     @Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http.authorizeRequests()
// 			.antMatchers("/managers/{id}/**").access("@userSecurity.hasId(authentication, #id)")
// 			.antMatchers("/trainers/**").access("false")
// 			.antMatchers("/learners/**").access("false")
// 			.anyRequest().authenticated()
// 			.and()
// 			.formLogin().defaultSuccessUrl("/managers/").permitAll()
// 			.and()
// 			.logout().permitAll()
// 			.and()
// 			.exceptionHandling().accessDeniedPage("/405")
// 			;
// 	}	
// }