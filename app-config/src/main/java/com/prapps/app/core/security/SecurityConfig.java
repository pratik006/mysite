package com.prapps.app.core.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.prapps.app.core.handler.DefaultAuthenticationSuccessHandler;
import com.prapps.app.core.security.jwt.JwtTokenProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/rest/secured/**";

	@Inject private UserDetailsService userDetailsService;
	//@Inject private RESTAuthenticationSuccessHandler authSuccesHandler;
	@Inject private DefaultAuthenticationSuccessHandler defaultAuthSuccessHandler;
	@Autowired private JwtTokenProcessingFilter jwtTokenProcessingFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/index.html", "/social/**", "/prism/**", "/resources/**", "/favicon.ico","/rest/login", "/rest/error/*",
						"/chat/index.html", "/chat/js/**", "/chat/css/**", "/chat/sounds/**", "/chat/app.js",
						"/blog/app.js", "/blog/model/*", "/blog/view/*", "/blog/router/*", "/blog/templates/*", "/blog/css/**", "/blog/index.html",
						"/raktimeshphotography", "/raktimesh/**","/rest/rail/**", "/rail/**",
						"/sitemap.xml", "/robots.txt"
						)
					.permitAll()
				.antMatchers(HttpMethod.POST, "/rest/blog/comment").permitAll()
				.antMatchers(HttpMethod.GET, "/rest/blog/**").permitAll()
				.antMatchers(HttpMethod.GET, "/blog/page/edit/**").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.GET, "/blog/page/**").permitAll()
				.antMatchers(HttpMethod.GET, "/rest/chat/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/rest/blog/*").permitAll()

				.antMatchers(HttpMethod.PUT, "/rest/blog/*").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.POST, "/rest/blog/*").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.POST, "/blog/page/save").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.PUT, "/blog/page/save").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.DELETE, "/rest/blog/*").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.DELETE, "/rest/blog/*").hasAnyRole("user", "admin")


				.antMatchers(HttpMethod.GET, "/rest/rail/**").permitAll()
				.antMatchers(HttpMethod.POST, "/rest/rail/**").permitAll()
				.antMatchers(HttpMethod.GET, "/chat/**").hasAnyRole("user", "admin")
				
				.antMatchers(HttpMethod.GET, "/rest/secured/chess").hasAnyRole("admin")
				
				//.antMatchers(HttpMethod.GET, "/rest/secured/**").hasAnyRole("user", "admin")
				.antMatchers("/rest/blogs/*").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().permitAll().loginPage("/login.html")
				.loginProcessingUrl("/blog/login")
				.successHandler(defaultAuthSuccessHandler)
				.failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex)
							throws IOException, ServletException {
						System.out.println(req);
					}
				})
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/rest/blog/logout")).logoutSuccessUrl("/login")
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
			.and()
	        .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated() // Protected API End-points
	        	.and()
	        		.addFilterBefore(jwtTokenProcessingFilter, UsernamePasswordAuthenticationFilter.class)
	        		.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	
	public AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				if ("application/json".equals(request.getContentType())) {
					response.sendRedirect(request.getContextPath()+"/error/unauthorised");
				} else {
					response.sendRedirect(request.getContextPath()+"/error");
				}
			}
		};
	}

}
