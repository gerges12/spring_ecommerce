package com.luv2code.ecommerce.service;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.ecommerce.dao.RoleRepository;
import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.dao.VerificationTokenRepository;
import com.luv2code.ecommerce.dto.AuthenticationResponse;
import com.luv2code.ecommerce.dto.LoginRequest;
import com.luv2code.ecommerce.dto.RegisterRequest;
import com.luv2code.ecommerce.entity.Role;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.entity.VerificationToken ;
import com.luv2code.ecommerce.exceptions.EmailAlreadyExistException;
import com.luv2code.ecommerce.exceptions.ProductAlreadyExistException;
import com.luv2code.ecommerce.security.JwtProvider;

import io.jsonwebtoken.security.InvalidKeyException;

@Service
public class AuthService {
	
	@Autowired
	RefreshTokenService refreshTokenService  ;
	
	@Autowired
     UserRepository  userrepository ;
	
	@Autowired
    JwtProvider  jwtProvider  ;
	
	@Autowired
	RoleRepository roleRepository  ;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
    VerificationTokenRepository verificationTokenRepository ;
	
	@Autowired
    AuthenticationManager authenticationManager ;
    

	public  void signup(RegisterRequest registerRequest) {
		
		
		if (userrepository.findByEmail(registerRequest.getEmail()).isPresent() ) 
		{
		 throw new EmailAlreadyExistException("email " +registerRequest.getEmail() + " is already exist ") ;
		}
		
		else if (userrepository.findByUsername(registerRequest.getUsername()).isPresent()) 
		{
		 throw new EmailAlreadyExistException("username " +registerRequest.getUsername() + " is already exist ") ;
		}
		
		   System.out.print("ggggggggggggg  " + registerRequest.isSponser());
		   

		 User user = new User();
	        user.setUsername(registerRequest.getUsername());
	        user.setEmail(registerRequest.getEmail());
	        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
	        user.setCreated(new Date());
	        user.set_sponser(registerRequest.isSponser());
	        user.setEnabled(false);
	        user.setTotal_of_orders(0);
	        user.setRoles(null);
	        userrepository.save(user);
	        
	        
	        
	        
	      //  String token = generateVerificationToken(user);
		
		
	}

	private  String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        

        verificationTokenRepository.save(verificationToken);
        return token;
	}
	
	
	
	public AuthenticationResponse login(LoginRequest  loginRequest) {
		Authentication authenticate = authenticationManager.
				authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
		
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();

		
		
		
	}

	 @Transactional(readOnly = true)
	    public User getCurrentUser() {
	        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
	                getContext().getAuthentication().getPrincipal();
	        return userrepository.findByUsername(principal.getUsername())
	                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
	    }

	public boolean if_user_vip() {
		User user  = getCurrentUser()  ;
		
		if (user.getTotal_of_orders() >= 1000)
		{
			return true;

		}
		
		else {
			return false  ;
		}
	}
	
	
	public boolean is_admin() {
		Set<Role> roles  = getCurrentUser().getRoles()  ;
		
		
		boolean b = false ;
		for (Role role : roles) {
			
			if ("ADMIN".equals(role.getName()))  
			{ 
				b = true ;
				break ;
			}
			
			 

		}
		
		return b ;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
