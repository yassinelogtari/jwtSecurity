package com.example.demo.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class authentifcationController {

	private final AuthenticationService service = new AuthenticationService();
	
	@PostMapping("/register")
	public ResponseEntity<AuthentificationResponse> register(@RequestBody RegisterRequest request){
		
		return ResponseEntity.ok(service.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthentificationResponse> authenticate(@RequestBody AuthenticationRequest request){
		
		return ResponseEntity.ok(service.authenticate(request));
	}
	

}
