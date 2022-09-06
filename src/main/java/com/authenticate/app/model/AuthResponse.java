package com.authenticate.app.model;

public class AuthResponse {
	
	private String jwt;

	public AuthResponse(String jwt) {
		super();
		this.jwt = jwt;
	}
	
	public String getJwt()
	{
		return jwt;
	}

	public AuthResponse()
	{
		
	}
}
