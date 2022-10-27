package com.example.groupcalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
    private UserDetailsService userDetailsService;	
	
	
	//Filterit jolla määritellään pääsy eri tapahtumiin
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/signup","/newuser").permitAll()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated() //authenticated vs permitAll
				.and()
				.csrf().ignoringAntMatchers("/h2-console/**")
				.and()
	        	  .headers().frameOptions().sameOrigin()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/list", true)
		          .permitAll()
		          .and()
		      .logout()
		      	.logoutSuccessUrl("/")
		          .permitAll()
		          .and()
			.httpBasic();
		return http.build();
			
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticate) throws Exception{
		authenticate.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
