package com.ufs.pdfalaufs.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	// mapea este metodo para a requisicao para a url contida no @GetMapping("/)
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		String senhaCodificada = encoder.encode(dto.getSenha());
		Usuario usuario = Usuario.builder().cpf(dto.getCpf()).ativo(dto.getAtivo()).email(dto.getEmail()).nome(dto.getNome()).telefonePrincipal(dto.getTelefonePrincipal()).senha(senhaCodificada).build();

		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}

