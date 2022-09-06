package com.authenticate.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenticate.app.model.AuthRequest;
import com.authenticate.app.model.AuthResponse;
import com.authenticate.app.services.MyUserDetails;
import com.authenticate.app.util.Jwtutil;

@RestController
//@RequestMapping("/authenticate")
public class AuthenticateController {

	@Autowired
	private MyUserDetails myuserdetails;

	@Autowired
	private Jwtutil jwtutil;

	@Autowired
	private AuthenticationManager authmanager;

	@PostMapping("/authenticate/getjwt")
	public ResponseEntity<?> getAuthenticationToken(@RequestBody AuthRequest request) throws Exception {

		try {
			authmanager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			final UserDetails userdetails = myuserdetails.loadUserByUsername(request.getUsername());
			String jwt1 = jwtutil.generateToken(userdetails);
			return ResponseEntity.ok(new AuthResponse(jwt1));
		} catch (BadCredentialsException e) {
			throw new Exception("Enter Valid username and password" + e);
		}
	}
	
	@GetMapping("/validatejwt")
	public boolean validateJwt()
	{
		return true;
	}

}
