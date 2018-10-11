package br.auth.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.auth.bc.UserService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader(HEADER_STRING);

		System.out.println("header=" + header);

		if (header != null && header.startsWith(TOKEN_PREFIX)) {
			String authToken = header.replace(TOKEN_PREFIX, "");
			System.out.println("authToken=" + authToken);

			// selecionar usuário do token

			// carregar usuário do banco

			// validar token não está expirado

			// colocar usuário no 'security context'
			String username = "aguilar";
			UserDetails userDetails = userService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			logger.info("authenticated user " + username + ", setting security context");
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// TODO gerar novo token
			response.addHeader("Authorization", "teste");
		}

		filterChain.doFilter(request, response);
	}

}
