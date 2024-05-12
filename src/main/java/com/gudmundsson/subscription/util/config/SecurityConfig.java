package com.gudmundsson.subscription.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable);
	    http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    http.authorizeHttpRequests(auth -> auth
//	        .requestMatchers(SWAGGER_WHITE_LIST).permitAll()
	        .requestMatchers("/**").permitAll()
//	        .requestMatchers(HttpMethod.GET,"/categories/**").permitAll()
//	        .requestMatchers(HttpMethod.POST, "/products").hasAuthority("ADMIN")
//	        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
//	        .requestMatchers(HttpMethod.POST, "/orders").hasAuthority("USER")
//	        .requestMatchers(HttpMethod.GET, "/orders/**").hasAuthority("USER")
//	        .requestMatchers(HttpMethod.POST, "/users").hasAuthority("ADMIN")
//	        .anyRequest().authenticated()
	    );
//	    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	  }
}
