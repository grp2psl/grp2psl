package com.psl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.dao.ITrainerDAO;
import com.psl.entities.Trainer;

@Service
public class TrainerUserDetails implements UserDetailsService {
	@Autowired
	private ITrainerDAO trainerRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trainer user = trainerRepository.findByEmail(username);
		
		if (user != null) {
			return User.builder()
                	.username(user.getEmail())
                	.password( bCryptPasswordEncoder.encode(user.getPassword()) )
                	.disabled(false)
                	.accountExpired(false)
                	.accountLocked(false)
                	.credentialsExpired(false)
                	.roles("TRAINER")
                	.build();
		}
		else{
			throw new UsernameNotFoundException("No user Found");
		}
	}

}
