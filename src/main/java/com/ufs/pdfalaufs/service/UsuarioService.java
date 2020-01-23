package com.ufs.pdfalaufs.service;

import java.util.Optional;

import com.ufs.pdfalaufs.model.entity.Usuario;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

public interface UsuarioService {
	
	//Metodo que recebe o email e senha e verifica se ja esta cadastrado  no sistema
	Usuario autenticar(String email, String senha);

	Usuario salvarUsuario(Usuario usuario) throws RegraNegocioException;
	
	void validarEmail(String email) throws RegraNegocioException;
	
	Optional<Usuario> obterPorId(Long id);
	
}