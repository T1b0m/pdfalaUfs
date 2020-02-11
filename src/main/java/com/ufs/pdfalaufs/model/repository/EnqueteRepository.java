package com.ufs.pdfalaufs.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ufs.pdfalaufs.model.entity.Eixo;
import com.ufs.pdfalaufs.model.entity.Enquete;

public interface EnqueteRepository extends JpaRepository<Enquete, Long> {

	@Query(value = "SELECT * FROM audiencias.enquete WHERE ativo = 1", nativeQuery = true)
	List<Eixo> findAllActive();
	
	Optional<Enquete> findByNome(String nome);
	
	Optional<Enquete> findByIdEnquete(UUID id);
	
}
