package com.authenticate.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthenticateClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateClientApplication.class, args);
	}

}
