package com.loanman.loanmanagement;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.ext.Provider;


@Provider
public class SecurityFilter implements ContainerRequestFilter{

	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		System.out.println(requestContext.getHeaders());
		 
		String authHeader = requestContext.getHeaderString("Authorization");
		if (authHeader == null || !authHeader.startsWith("Basic")) {
			requestContext.abortWith(Response.status(401).header("WWW-Authenticate", "Basic").build());
			return;
		}

		String[] tokens = (new String(Base64.getDecoder().decode(authHeader.split(" ")[1]), "UTF-8")).split(":");
		final String username = tokens[0];
		final String password = tokens[1];

		System.out.println("user name = " + username+"password");
		if (username.equals("daniel") && password.equals("123")) {
			// all good
		}
		else {
			requestContext.abortWith(Response.status(401).build());
			return;
		}
		
	}
  }

	

