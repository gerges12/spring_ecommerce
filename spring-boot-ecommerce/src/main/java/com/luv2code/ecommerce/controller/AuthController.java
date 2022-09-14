package com.luv2code.ecommerce.controller;

import static org.springframework.http.HttpStatus.OK;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dto.AuthenticationResponse;
import com.luv2code.ecommerce.dto.LoginRequest;
import com.luv2code.ecommerce.dto.RefreshTokenRequest;
import com.luv2code.ecommerce.dto.RegisterRequest;
import com.luv2code.ecommerce.service.AuthService;
import com.luv2code.ecommerce.service.RefreshTokenService;

import io.jsonwebtoken.security.InvalidKeyException;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
	private AuthService authservice;
    
    @Autowired
    private RefreshTokenService refreshTokenService  ;

	@PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {

		authservice.signup(registerRequest); 
        return new ResponseEntity<>("User Registration Successful", OK);
    }
	
	@PostMapping("/login")
	public AuthenticationResponse signin(@RequestBody LoginRequest  logiRequest ) {
        return authservice.login(logiRequest)  ;

	}
	
	 @PostMapping("/logout")
	    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
	        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
	        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
	    }
	
	
	
	
	
	@GetMapping("/AreYouVip")
	public boolean signin()  {
        return authservice.if_user_vip()  ;

	}
	
	
}
