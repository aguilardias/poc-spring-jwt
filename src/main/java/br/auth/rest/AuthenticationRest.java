package br.auth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.auth.dto.LoginUser;

@RestController
@RequestMapping("/token")
public class AuthenticationRest {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/generate-token")
	public ResponseEntity register(@RequestBody LoginUser loginUser) {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// final String token = jwtTokenUtil.generateToken(loginUser);
		final String token = "token123";

		return ResponseEntity.ok(token);
	}
}
