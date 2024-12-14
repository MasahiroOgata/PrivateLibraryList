package com.library.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.anyRequest().authenticated()
		);
		
		http.headers(headers -> headers
				.frameOptions(FrameOptionsConfig::disable)
				);
		
		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login?error")
				.usernameParameter("user")
				.passwordParameter("pass")
				.defaultSuccessUrl("/book/list", true)
				.permitAll()
		).logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
//				.permitAll()
		);
		
		// CSRF 対策を無効に設定 (一時的)
//        http.csrf(csrf -> csrf
//                .disable()
//        );

		
		
		return http.build();
	}
	
	// インメモリ認証
		
	/*
		@Bean
	    InMemoryUserDetailsManager userDetailsService() {
			
			PasswordEncoder encoder = passwordEncoder();
			
	        UserDetails user = User.withUsername("user")
	        		.password(encoder.encode("user"))
	                .roles("GENERAL")
	                .build();
	        
	        return new InMemoryUserDetailsManager(user);
	    }
	 */ 

}
