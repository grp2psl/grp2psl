package com.psl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;

import com.psl.service.LearnerService;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityCOnfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
	private javax.sql.DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new PasswordEncoderTest();
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("select email, password, enabled from learner where email=?;")
			.authoritiesByUsernameQuery("select email, role from learner where email=?;")
			;
	}


	
	// @Autowired
	// private LearnerService service;

	// private int getLearneridByEmail(String email) {
	// 	return service.getLearneridByEmail(email);
	// }

	// private String getLearnerId() {
	// 	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// 	String email = auth.getName();
	// 	int learnerid = getLearneridByEmail(email);
	// 	String path = String.format("/learners/%d/", learnerid);
	// 	return path;
	// }



	// private int getLearnerId() {
	// 	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// 	String email = auth.getName();
	// 	LearnerService service = new LearnerService();
	// 	int learnerid = service.getLearneridByEmail(email);
	// 	return learnerid;
	// }

	// public Learner getLearnerId() {
	// 	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// 	String email = auth.getName();
	// 	LearnerService service;
	// 	String learnerid = service.getByEmail(email);
	// 	return learnerid;

	// }
	
    // public String currentUserId() {
    //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	//     String currentUserName = auth.getName();
    //     Int id = @Query("select email, role from learner where email=?;");
    // }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/*").hasRole("USER")
			.anyRequest().authenticated()
			.and()
			.formLogin().defaultSuccessUrl("/learners/1/").permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}	
}

// @Query("select learnerid from learner where email=?;");

