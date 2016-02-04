package com.prapps.app.core.secutiry;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.prapps.app.core.api.TrafficService;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	private static final Logger log = Logger.getLogger(CorsFilter.class);
	
	private TrafficService trafficService;
	private @Value("${urls.to.log}") String loggableUrls;
	private Set<String> loggableUrlSet = new HashSet<String>();
	
	@Inject
	public CorsFilter(TrafficService trafficService) {
		this.trafficService = trafficService;
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("loggableUrls: " + loggableUrls);
		for (String url : loggableUrls.split(";")) {
			loggableUrlSet.add(url);
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String remoteAddr = servletRequest.getRemoteAddr();
		String uri = request.getRequestURI();
		 if (loggableUrlSet.contains(uri)) {
			 String hash;
			try {
				hash = makeSHA1Hash(remoteAddr);
				trafficService.logTraffic(hash, uri);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS, PUT");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
	
	 public String makeSHA1Hash(String input)
	            throws NoSuchAlgorithmException, UnsupportedEncodingException
	        {
	            MessageDigest md = MessageDigest.getInstance("SHA1");
	            md.reset();
	            byte[] buffer = input.getBytes("UTF-8");
	            md.update(buffer);
	            byte[] digest = md.digest();

	            String hexStr = "";
	            for (int i = 0; i < digest.length; i++) {
	                hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
	            }
	            return hexStr;
	        }
}