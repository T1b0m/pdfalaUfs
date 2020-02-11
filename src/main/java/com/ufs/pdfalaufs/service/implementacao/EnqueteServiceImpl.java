package com.ufs.pdfalaufs.service.implementacao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufs.pdfalaufs.model.entity.Enquete;
import com.ufs.pdfalaufs.model.repository.EnqueteRepository;
import com.ufs.pdfalaufs.service.EnqueteService;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

@Service
public class EnqueteServiceImpl implements EnqueteService {

	@Autowired
	private EnqueteRepository repository;
	
	public EnqueteServiceImpl(EnqueteRepository repositoy) {
		this.repository = repositoy;
	}

	@Override
	public Enquete salvarEnquete(Enquete enquete) throws RegraNegocioException {
		return repository.save(enquete);
	}

	@Override
	public Enquete atualizarEnquete(Enquete enquete) throws RegraNegocioException {
		Objects.requireNonNull(enquete.getNome());
		validar(enquete);
		return repository.save(enquete);
	}

	@Override
	public void deletarEnquete(Enquete enquete) {
		Objects.requireNonNull(enquete.getNome());
		repository.delete(enquete);
	}

	@Override
	public Optional<Enquete> findByNome(String nome) {
		return repository.findByNome(nome);
	}

	@Override
	public Optional<Enquete> findByIdEnquete(UUID id) {
		return repository.findByIdEnquete(id);
	}

	@Override
	public List<Enquete> findAll() {
		return repository.findAll();
	}
	
	@Override
	public void validar(Enquete enquete) throws RegraNegocioException {
		if(enquete.getNome().trim().equals("")) throw new RegraNegocioException("Informe um nome valido");
	}
}
