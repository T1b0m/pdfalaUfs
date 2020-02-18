package com.ufs.pdfalaufs.api.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufs.pdfalaufs.api.dto.UsuarioDTO;
import com.ufs.pdfalaufs.model.entity.Usuario;
import com.ufs.pdfalaufs.service.UsuarioService;
import com.ufs.pdfalaufs.service.exception.ErroAutenticacao;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

	private final UsuarioService service;
	private final PasswordEncoder encoder;
	
	@PostMapping("/autenticar")
	public ResponseEntity<?> autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	// mapea este metodo para a requisicao para a url contida no @GetMapping("/)
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody UsuarioDTO dto) {
		String senhaCodificada = encoder.encode(dto.getSenha());
		Usuario usuario = Usuario.builder().cpf(dto.getCpf()).ativo(dto.getAtivo()).email(dto.getEmail()).nome(dto.getNome()).telefonePrincipal(dto.getTelefonePrincipal()).senha(senhaCodificada).build();

		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<?> buscar(){
		List<Usuario> usuarios = service.findAll();
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") UUID idUsuario){
		Optional<Usuario> usuario = service.obterPorId(idUsuario);
		return usuario.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}

