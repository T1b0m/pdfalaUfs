package com.ufs.pdfalaufs.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufs.pdfalaufs.model.entity.Usuario;

//nos fornece os metodos padroes de salvar atualizar, deletar, consultar
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//busca composta colocar findByEmailAndNome( String emailm String nome);
		//Optional<Usuario> findyByEmail(String email);(Traz o objeto inteiro)
		boolean existsByEmail(String email);
		
		boolean existsByCpf(String cpf);
		
		Optional<Usuario> findByEmail(String email);
		
		Optional<Usuario> findByIdUsuario(UUID idEixo);
}
