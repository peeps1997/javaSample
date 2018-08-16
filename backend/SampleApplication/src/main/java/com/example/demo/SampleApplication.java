package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity 
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {

		/**
		 * This section defines the user accounts which can be used for authentication as well as the roles each user has.
		 */
		@Bean
		InMemoryUserDetailsManager userDetailsManager() {

			@SuppressWarnings("deprecation")
			UserBuilder builder = User.withDefaultPasswordEncoder();

			UserDetails greg = builder.username("greg").password("turnquist").roles("USER").build();
			UserDetails ollie = builder.username("ollie").password("gierke").roles("USER", "ADMIN").build();

			return new InMemoryUserDetailsManager(greg, ollie);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.httpBasic().and().authorizeRequests().
					antMatchers("/**").hasRole("ADMIN").and().
					csrf().disable();
		}
	}
	
}
	
	