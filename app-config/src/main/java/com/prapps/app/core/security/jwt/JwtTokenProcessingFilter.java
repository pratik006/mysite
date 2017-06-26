package com.prapps.app.core.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import com.prapps.app.core.dto.User;
import com.prapps.app.core.dto.UserDetailsImpl;
import com.prapps.app.core.security.rest.RestAuthenticationFailureHandler;

@Component
public class JwtTokenProcessingFilter extends AbstractAuthenticationProcessingFilter {
	private final String TOKEN_FILTER_APPLIED = "TOKEN_FILTER_APPLIED";

	@Autowired
	public JwtTokenProcessingFilter(@Qualifier("restAuthenticationManager") AuthenticationManager authenticationManager,
			RestAuthenticationFailureHandler restAuthenticationFailureHandler) {
		super("/rest/secured/**");
		super.setAuthenticationManager(authenticationManager);
		setAuthenticationSuccessHandler(new TokenBasedAuthenticationSuccessHandlerImpl());
		setAuthenticationFailureHandler(restAuthenticationFailureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		request.setAttribute(TOKEN_FILTER_APPLIED, Boolean.TRUE);
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Bearer ")) {
			//throw new JwtTokenMissingException("No JWT token found in request headers");
			//throw new RuntimeException("No JWT token found in request headers");
			throw new AuthenticationCredentialsNotFoundException("No JWT token found in request headers");
		}

		String authToken = header.substring(7);
		User user = JwtTokenHelper.verifyToken(authToken);
		UserDetails userDetails = new UserDetailsImpl(user);

		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	}

	/*@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);

		// As this authentication is in HTTP header, after success we need to
		// continue the request normally
		// and return the response as if the resource was not secured at all
		chain.doFilter(request, response);
	}*/

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		if (request.getAttribute(TOKEN_FILTER_APPLIED) != null) {
			arg2.doFilter(request, response);
		} else {
			super.doFilter(arg0, arg1, arg2);
		}
	}

}