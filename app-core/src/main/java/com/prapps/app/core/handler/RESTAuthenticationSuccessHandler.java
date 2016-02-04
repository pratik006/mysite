package com.prapps.app.core.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prapps.app.core.dto.User;
import com.prapps.app.core.dto.UserDetailsImpl;

@Component
public class RESTAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger LOG = Logger.getLogger(RESTAuthenticationSuccessHandler.class);
	
    private ObjectMapper mapper;
    @Autowired 
    @Qualifier("mappingJackson2HttpMessageConverter")
    MappingJackson2HttpMessageConverter messageConverter;

   /* @Autowired @Qualifier("mappingJackson2HttpMessageConverter")
    RESTAuthenticationSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }*/

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        user.setPassword(null);

        LOG.info(user.getUserName() + " got is connected ");

        PrintWriter writer = response.getWriter();
        messageConverter.getObjectMapper().writeValue(writer, userDetails);
        writer.flush();
    }
}
