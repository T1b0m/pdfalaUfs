package com.ufs.pdfalaufs.api.dto;

import java.util.Date;

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
public class EixoDTO {

	private String visivel;
	private String titulo;
	private String descricao;
	private String ativo;
	
	@Builder.Default
	private Date dtAlt = new java.sql.Date(System.currentTimeMillis());
}
