package com.ufs.pdfalaufs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ufs.pdfalaufs.model.entity.Usuario;
import com.ufs.pdfalaufs.service.UsuarioService;
import com.ufs.pdfalaufs.service.exception.ErroAutenticacao;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.obterProEmail(email);
		
		if(usuario == null) throw new ErroAutenticacao("Usuario nao encontrado para o email informado");
		
		return User.builder().username(usuario.getEmail()).password(usuario.getSenha()).authorities(new SimpleGrantedAuthority("USER")).build();
		
	}

}
