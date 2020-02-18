package com.ufs.pdfalaufs.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ufs.pdfalaufs.model.entity.Usuario;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

public interface UsuarioService {
	
	//Metodo que recebe o email e senha e verifica se ja esta cadastrado  no sistema
	Usuario autenticar(String email, String senha);

	Usuario salvarUsuario(Usuario usuario) throws RegraNegocioException;
	
	Usuario obterProEmail(String email);
	
	void validarEmail(String email) throws RegraNegocioException;
	
	void validarCpf(String cpf) throws RegraNegocioException;
	
	void verificaCpfValido(String cpf) throws RegraNegocioException;
	
	Optional<Usuario> obterPorId(UUID id);
	
	List<Usuario> findAll();
	
	
}
