package com.Social11.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Social11.config.JwtAuthenticationBuilder;
import com.Social11.config.JwtAuthenticationEntryPoint;
import com.Social11.helper.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().
		authorizeHttpRequests()
			.antMatchers("/createUser").permitAll()
			.antMatchers("/CreateProfile").permitAll()
			.antMatchers("/otpform").permitAll()
			.antMatchers("/forgot").permitAll()
			.antMatchers("/admin").authenticated()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/harmeet").authenticated()
			.antMatchers("/login").permitAll()
			.and().
			exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.jdbcAuthentication()
//			.dataSource(datasource)
//			.usersByUsernameQuery("select username,password,enabled from user123 where username=?")
//			.authoritiesByUsernameQuery("select username,role from user123 where username=?")
//			.passwordEncoder(new BCryptPasswordEncoder());
//	}	
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customuserDetails);
//	}
//	
	@Bean
	public AuthenticationManager authenticationmanagerbean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
}