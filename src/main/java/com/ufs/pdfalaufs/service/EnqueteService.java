package com.ufs.pdfalaufs.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ufs.pdfalaufs.model.entity.Enquete;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

public interface EnqueteService {

	Enquete salvarEnquete(Enquete enquete) throws RegraNegocioException;	
	Enquete atualizarEnquete(Enquete enquete) throws RegraNegocioException;
	
	void deletarEnquete(Enquete enquete);
	void validar(Enquete enquete) throws RegraNegocioException;
	
	Optional<Enquete> findByNome(String nome);
	Optional<Enquete> findByIdEnquete(UUID id);
	
	List<Enquete> findAll();
	//implementar por tipo de id eixo
}
