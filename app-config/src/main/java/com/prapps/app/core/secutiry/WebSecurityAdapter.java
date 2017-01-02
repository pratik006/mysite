package com.prapps.app.core.secutiry;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.prapps.app.core.handler.DefaultAuthenticationSuccessHandler;
import com.prapps.app.core.handler.HttpAuthenticationEntryPoint;
import com.prapps.app.core.handler.RESTAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

	@Inject private UserDetailsService userDetailsService;
	@Inject private RESTAuthenticationSuccessHandler authSuccesHandler;
	@Inject private DefaultAuthenticationSuccessHandler defaultAuthSuccessHandler;
	@Inject private HttpAuthenticationEntryPoint authEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/index.html", "/bootstrap/**", "/social/**", "/prism/**", "/resources/**",
						"/chat/index.html", "/chat/js/**", "/chat/css/**", "/chat/sounds/**", "/chat/app.js",
						"/blog/app.js", "/blog/model/*", "/blog/view/*", "/blog/router/*", "/blog/templates/*", "/blog/css/**", "/blog/index.html"
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
				.antMatchers(HttpMethod.DELETE, "/rest/blog/*").hasAnyRole("user", "admin")
				.antMatchers(HttpMethod.DELETE, "/rest/blog/*").hasAnyRole("user", "admin")
				
				.antMatchers(HttpMethod.GET, "/chat/**").hasAnyRole("user", "admin")
				.antMatchers("/rest/blogs/*").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().permitAll().loginPage("/login.html")
				.loginProcessingUrl("/rest/blog/login")
				.successHandler(authSuccesHandler)
				//.defaultSuccessUrl("/rest/userinfo", false)
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
				.formLogin().permitAll().loginPage("/login.html")
				.loginProcessingUrl("/blog/login")
				.successHandler(defaultAuthSuccessHandler)
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/rest/blog/logout")).logoutSuccessUrl("/login");
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	
}
