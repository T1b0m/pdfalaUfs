package com.ufs.pdfalaufs.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ufs.pdfalaufs.model.entity.Eixo;

public interface EixoRepository extends JpaRepository<Eixo, Long>{
	
	@Query(value = "SELECT * FROM audiencias.eixo WHERE ativo = 1", nativeQuery = true)
	List<Eixo> findAllActive();


	Optional<Eixo> findByTitulo(String titulo);
	
	Optional<Eixo> findByIdEixo(UUID idEixo);
}
