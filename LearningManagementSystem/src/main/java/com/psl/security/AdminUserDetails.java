package com.psl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.dao.IManagerDAO;
import com.psl.entities.Manager;

@Service
public class AdminUserDetails implements UserDetailsService {

	@Autowired
	private IManagerDAO managerRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Manager user = managerRepository.findByEmail(username);
		
		if (user != null) {
			return User.builder()
                	.username(user.getEmail())
                	.password( bCryptPasswordEncoder.encode(user.getPassword()) )
                	.disabled(false)
                	.accountExpired(false)
                	.accountLocked(false)
                	.credentialsExpired(false)
                	.roles("ADMIN")
                	.build();
		}
		else{
			throw new UsernameNotFoundException("No user Found");
		}
	}

}
