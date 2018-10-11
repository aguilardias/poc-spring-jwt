package br.user.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<String> listar() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		return Arrays.asList("user1");
	}
}
