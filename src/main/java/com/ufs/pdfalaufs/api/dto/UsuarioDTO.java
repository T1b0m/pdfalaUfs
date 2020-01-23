package com.ufs.pdfalaufs.api.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private String cpf;
	private String ativo;
	private String email;
	private String nome;
	private String telefonePrincipal;
	private String senha;
}
