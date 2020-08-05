package com.contratacion.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.contratacion.proyecto.models.services.UsuarioService;
import com.contratacion.proyecto.security.LoginSuccessHandler;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private LoginSuccessHandler handler;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Autowired //Authetication
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{	
		build.userDetailsService(service).passwordEncoder(encoder());		
	}
	
	@Override //Autorization
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/dist/**","/docs/**","/pages/**","/plugins/**","/inicio/**").permitAll()
			.antMatchers("/cargo/**").anonymous()
			.antMatchers("/penalidad/**").hasAnyRole("ADMIN")
			.antMatchers("/usuario/**").anonymous()
			.antMatchers("/roldepago/**").hasAnyRole("ADMIN")
			.antMatchers("/sancion/**").hasAnyRole("ADMIN")			
			.antMatchers("/trabajador/**").hasAnyRole("ADMIN")			
			.antMatchers("/h2-console/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin().successHandler(handler).loginPage("/login").permitAll()			
			.and().logout().permitAll()			
			.and().exceptionHandling().accessDeniedPage("/error_403")
			.and()
				.csrf().ignoringAntMatchers("/h2-console/**")
			.and()
				.headers().frameOptions().sameOrigin();
		http.formLogin().defaultSuccessUrl("/sistema.html",true);
		http.logout().logoutSuccessUrl("/index.html");
	}
}