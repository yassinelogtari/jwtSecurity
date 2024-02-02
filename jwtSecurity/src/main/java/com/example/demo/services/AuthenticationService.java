package com.example.demo.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.auth.AuthenticationRequest;
import com.example.demo.auth.AuthentificationResponse;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.entities.Role;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private UserRepository repository ;
	
	public AuthentificationResponse register(RegisterRequest request) {
		
		var user = User.builder()
				.firstname(request.getFirstname())
				.firstname(request.getLastname())
				.firstname(request.getEmail())
				.password(PasswordEncoder.ecode(request.getPassword()))
				.role(Role.USER)
				.build();
		repository.save(user);
	}

	public Object authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

