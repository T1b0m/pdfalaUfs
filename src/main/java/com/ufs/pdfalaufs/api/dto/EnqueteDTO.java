package com.ufs.pdfalaufs.api.dto;

import java.util.Date;
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
public class EnqueteDTO {

	//analisar se necessita da data inc
	private String nome;
	private String datVenc;
	private String descricao;
	private String ativo;
	private String visivel;
	private UUID idEixo;
	private String pergunta;
	
	@Builder.Default
	private Date dtAlt = new java.sql.Date(System.currentTimeMillis());
	
}
