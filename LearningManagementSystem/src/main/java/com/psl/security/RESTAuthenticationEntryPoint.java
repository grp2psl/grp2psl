package com.psl.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RESTAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		super.commence(request, response, authException);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}

    @Override
    public void afterPropertiesSet() {
        setRealmName("LearningManagementSystem");
        super.afterPropertiesSet();
    }
}
