package com.Social11.controller;

import com.Social11.Dao.JwtBlackListRepository;
import com.Social11.helper.JwtTokenUtil;
import com.Social11.helper.JwtUserDetailService;
import com.Social11.models.JwtBlackList;
import com.Social11.models.JwtRequest;
import com.Social11.models.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailService userDetailsService;
	
	@Autowired
	private JwtBlackListRepository jwtRepository;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println(authenticationRequest.getUsername());
		System.out.println(authenticationRequest.getPassword());
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		UserLogin userLogin = new UserLogin();
		userLogin.setAccess_token(token);
		userLogin.setUserDetails(userDetails);

		return ResponseEntity.ok(userLogin);
	}
	
	@PostMapping("/logout1")
	public Map<String,String> logoutuserwithtoken(@RequestBody Map<String,String> json){
		String token = json.get("jwt");
		System.out.println(token);
	    JwtBlackList jwtBlacklist = new JwtBlackList();
	    jwtBlacklist.setToken(token);
	    jwtRepository.save(jwtBlacklist);
	    Map<String,String> mp = new HashMap<>();
	    mp.put("response", "success");
	    mp.put("msg", "user logout succesfully");
	    return mp;
	}
	
	private void authenticate(String username, String password) throws Exception {
		System.out.println("working");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
