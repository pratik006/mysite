package com.prapps.app.core.secutiry;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.prapps.app.core.handler.HttpAuthenticationEntryPoint;
import com.prapps.app.core.handler.RESTAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

	@Inject	private UserDetailsService userDetailsService;
	@Inject	RESTAuthenticationSuccessHandler authSuccesHandler;
	@Inject HttpAuthenticationEntryPoint authEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/index.html", "/bootstrap/**", "/blog/css/**", "/blog/index.html", "/blog/blog.html", "/blog/app.js", "/rest/blog/blogs").permitAll()
				.antMatchers(HttpMethod.PUT, "/rest/blog/*").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.POST, "/rest/blog/*").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.DELETE, "/rest/blog/*").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.OPTIONS, "/rest/blog/*").permitAll()
				.antMatchers(HttpMethod.GET, "/rest/blog/*").permitAll()
				.antMatchers("/rest/blogs/*").permitAll()
				.antMatchers("/**").permitAll()
				//.antMatchers("/**").hasRole("admin")
				.	anyRequest().authenticated()
			.and()
				.formLogin().permitAll()//.loginPage("/Login.html")
				.loginProcessingUrl("/rest/blog/login")
				.successHandler(authSuccesHandler)
				//.defaultSuccessUrl("/rest/userinfo", false)
				.usernameParameter("username")
				.passwordParameter("password")
			.and().logout().logoutUrl("/form/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	
}
