package com.comcast.service;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class JaxRsFilterAuthentication implements ContainerRequestFilter {
	public static final String AUTHENTICATION_HEADER = "Authorization";
	
	@Override
	public ContainerRequest filter(ContainerRequest arg0) {
		// TODO Auto-generated method stub
		return null;
		
		
		
		
		
		
	}

	
	
}
