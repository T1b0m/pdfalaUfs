package com.ufs.pdfalaufs.service.implementacao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufs.pdfalaufs.model.entity.Usuario;
import com.ufs.pdfalaufs.model.repository.UsuarioRepository;
import com.ufs.pdfalaufs.service.UsuarioService;
import com.ufs.pdfalaufs.service.exception.ErroAutenticacao;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	
	//alterado da video aula, TIRAR SE DA PEOBLEMA
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		//Verifica se o usario esta no sistema
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario nao encontrado para o email informado");
		}
		
		//verifica se a senha do usuario confere
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha invalida");
		}
		
		return usuario.get();
	}

	@Override
	//criar uma transacao na base de dados executa o metodo de salvar o usuario e dps commita
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) throws RegraNegocioException {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) throws RegraNegocioException {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Ja existe um usuario cadastrado com este email");
		}
	}

	@Override
	public Optional obterPorId(Long id) {
		return repository.findById(id);
	}

}
