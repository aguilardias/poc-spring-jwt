package br.auth.bc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// JwtUser jwtUser = new JwtUser();
		// jwtUser.setUsername(username);
		// jwtUser.setPassword(bcryptEncoder.encode("123"));
		// return jwtUser;
		return new User(username, bcryptEncoder.encode("123"), Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
	}
}
