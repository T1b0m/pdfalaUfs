package com.ufs.pdfalaufs.api.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufs.pdfalaufs.api.dto.EnqueteDTO;
import com.ufs.pdfalaufs.model.entity.Eixo;
import com.ufs.pdfalaufs.model.entity.Enquete;
import com.ufs.pdfalaufs.service.EixoService;
import com.ufs.pdfalaufs.service.EnqueteService;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enquete")
@RequiredArgsConstructor
public class EnqueteResource {

	private final EnqueteService service;
	private final EixoService eixoService;

	@PostMapping
	public ResponseEntity salvar(@RequestBody EnqueteDTO dto) throws RegraNegocioException {
		Enquete enquete = converter(dto);

		try {

			Enquete enqueteSalva = service.salvarEnquete(enquete);
			return new ResponseEntity(enqueteSalva, HttpStatus.CREATED);

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

	@GetMapping
	public ResponseEntity buscar() {
		List<Enquete> enquetes = service.findAll();
		return ResponseEntity.ok(enquetes);
	}

	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") UUID id, @RequestBody EnqueteDTO dto) {
		return service.findByIdEnquete(id).map(entity -> {
			try {
				Enquete enquete = converter(dto);
				enquete.setIdEnquete(entity.getIdEnquete());
				enquete.setDatAlt(dto.getDtAlt());
				service.atualizarEnquete(enquete);
				return ResponseEntity.ok(enquete);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Enquete nao encontrado na base", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") UUID id) {
		return service.findByIdEnquete(id).map(entity -> {
			service.deletarEnquete(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Enquete nao encontrado na base", HttpStatus.BAD_REQUEST));

	}
	
	private Enquete converter(EnqueteDTO dto) throws RegraNegocioException {
		Enquete enquete = new Enquete();
		SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
		
		enquete.setNome(dto.getNome());
		Date data = null;
		try {
			data = parser.parse(dto.getDatVenc());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		enquete.setDatVenc(dataSql);
		enquete.setDescricao(dto.getDescricao());
		enquete.setAtivo(dto.getAtivo());
		enquete.setVisivel(dto.getVisivel());
		enquete.setPergunta(dto.getPergunta());

		Eixo eixo = eixoService.obterPorId(dto.getIdEixo())
				.orElseThrow(() -> new RegraNegocioException("Eixo nao encontrado"));
		enquete.setEixo(eixo);

		return enquete;
	}
}
