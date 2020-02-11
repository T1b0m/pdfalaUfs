package com.ufs.pdfalaufs.api.resource;

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

import com.ufs.pdfalaufs.api.dto.EixoDTO;
import com.ufs.pdfalaufs.model.entity.Eixo;
import com.ufs.pdfalaufs.service.EixoService;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/eixo")
@RequiredArgsConstructor
public class EixoResource {

	private final EixoService service;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody EixoDTO dto) throws RegraNegocioException {
		Eixo eixo = converter(dto);

		try {
			Eixo eixoSalvo = service.salvarEixo(eixo);
			return new ResponseEntity(eixoSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity buscar(){
		List<Eixo> eixos = service.findAll();
		return ResponseEntity.ok(eixos);
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") UUID id, @RequestBody EixoDTO dto) {
		return service.obterPorId(id).map(entity -> {
			try {
				Eixo eixo =  converter(dto);
				eixo.setIdEixo(entity.getIdEixo());
				eixo.setDatAlt(dto.getDtAlt());
				service.atualizar(eixo);
				return ResponseEntity.ok(eixo);
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Eixo nao encontrado na base", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") UUID id) {
		return service.obterPorId(id).map(entidade -> {
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Eixo nao encontrado na base", HttpStatus.BAD_REQUEST));

	}
	
	private Eixo converter(EixoDTO dto) throws RegraNegocioException {

		Eixo eixo = new Eixo();

		eixo.setVisivel(dto.getVisivel());
		eixo.setTitulo(dto.getTitulo());
		eixo.setDescricao(dto.getDescricao());
		eixo.setAtivo(dto.getAtivo());

		return eixo;
	}
}
