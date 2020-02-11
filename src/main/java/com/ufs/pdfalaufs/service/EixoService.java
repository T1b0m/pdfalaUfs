package com.ufs.pdfalaufs.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ufs.pdfalaufs.model.entity.Eixo;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

public interface EixoService {

	Eixo salvarEixo(Eixo eixo) throws RegraNegocioException;
	
	Optional<Eixo> obterPorId(UUID id);
	Optional<Eixo> obterPorTitulo(String titulo);
	
	Eixo atualizar(Eixo eixo) throws RegraNegocioException;
	void deletar(Eixo eixo);
	void validar(Eixo eixo) throws RegraNegocioException;
	
	List<Eixo> buscar(Eixo eixooFiltro);
	List<Eixo> findAll();
	
	List<Eixo> buscarViveis(String visivel) throws RegraNegocioException;
	
}
