package com.prapps.app.core.secutiry;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

	@Inject
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/index.html", "/bootstrap/**", "/blog/css/**", "/blog/index.html", "/blog/blog.html", "/blog/app.js", "/rest/blog/blogs").permitAll()
				.antMatchers("/**").permitAll()
				.antMatchers("/rest/blog/*").permitAll()
				.antMatchers("/blog/create*/**").hasAnyRole("user", "admin")
				//.antMatchers("/**").hasRole("admin")
				.	anyRequest().authenticated()
			.and()
				.formLogin().permitAll().loginPage("/Login.html").loginProcessingUrl("/rest/blog/login")
					.successHandler(successHandler()).defaultSuccessUrl("/blog/index.html", false)
				.usernameParameter("username").passwordParameter("password")
			.and().logout().logoutUrl("/form/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
	}

	@Bean
	public static AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new RESTAuthenticationSuccessHandler();
		handler.setDefaultTargetUrl("/blog/");
		handler.setUseReferer(true);
		return handler;
	}

	@Configuration
	public static class RESTAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			String redirectPage = request.getParameter("redirectPage");
			System.out.println(redirectPage);
			setDefaultTargetUrl(redirectPage);
			clearAuthenticationAttributes(request);
		}
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
	
}
