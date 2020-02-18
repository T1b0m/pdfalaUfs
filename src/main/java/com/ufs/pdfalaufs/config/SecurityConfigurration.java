package com.ufs.pdfalaufs.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;

import com.ufs.pdfalaufs.security.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfigurration extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	//gerencia a configuracao de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(null).passwordEncoder(passwordEncoder); CONFIGURAR O USER DETAILS SERVICE E CONFIGURAR O PASSWORD ENCODER
		//String senhaCodificada = PasswordEncoder().encode(@123);
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	//configura as permissoes, dos usuarios
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//csfr recurso de seguranca de aplicacoes web
		http
		.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/usuarios", "/api/usuarios/autenticar").permitAll()
			.antMatchers("/**/*.{js,html,css}").permitAll()
            .antMatchers("/login").permitAll()
				.anyRequest().authenticated()
					.and().cors()
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public RequestCache refererRequestCache() {
	    return new HttpSessionRequestCache() {
	        @Override
	        public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
	            String referrer = request.getHeader("referer");
	            if (referrer != null) {
	                request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer));
	            }
	        }
	    };
	}

}
 