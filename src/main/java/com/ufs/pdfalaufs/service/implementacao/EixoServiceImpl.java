package com.ufs.pdfalaufs.service.implementacao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufs.pdfalaufs.model.entity.Eixo;
import com.ufs.pdfalaufs.model.repository.EixoRepository;
import com.ufs.pdfalaufs.service.EixoService;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

@Service
public class EixoServiceImpl implements EixoService{

	@Autowired
	private EixoRepository repository;
	
	//alterado da video aula, TIRAR SE DA PEOBLEMA
	
	public EixoServiceImpl(EixoRepository repository) {
		super();
		this.repository = repository;
	}
	
	
	@Override
	@Transactional
	public Eixo salvarEixo(Eixo eixo) throws RegraNegocioException {
		return repository.save(eixo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Eixo> obterPorTitulo(String titulo) {
		return repository.findByTitulo(titulo);
	}

	@Override
	@Transactional
	public Eixo atualizar(Eixo eixo) throws RegraNegocioException{
		Objects.requireNonNull(eixo.getTitulo());
		validar(eixo);
		return repository.save(eixo);
	}
	@Override
	@Transactional
	public void deletar(Eixo eixo) {
		Objects.requireNonNull(eixo.getTitulo());
		repository.delete(eixo);
	}

	@Override
	public List<Eixo> buscar(Eixo eixooFiltro) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Eixo> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Eixo> buscarViveis(String visivel) throws RegraNegocioException {
		return repository.findAllActive();
	}


	@Override
	public Optional<Eixo> obterPorId(UUID id) {
		return repository.findByIdEixo(id);
	}
	
	@Override
	public void validar(Eixo eixo) throws RegraNegocioException {
		if( eixo.getTitulo().trim().equals("")) {
			throw new RegraNegocioException("Informe uma titulo valido");
		}	
	}

	
}
