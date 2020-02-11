package com.ufs.pdfalaufs.model.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Enquete", schema = "audiencias")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enquete {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id_enquete")
	private UUID idEnquete;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "dt_venc")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datVenc;
	
	@Builder.Default
	@Column(name = "dt_inc")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datInc = new java.sql.Date(System.currentTimeMillis());
	
	@Column(name = "dt_alt")
	@Temporal(TemporalType.TIMESTAMP)    
	private Date datAlt;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "ativo")
	private String ativo;
	
	@Column(name = "visivel")
	private String visivel;
	
	@ManyToOne
	@JoinColumn(name = "id_eixo")
	private Eixo eixo;
	
	@Column(name = "pergunta")
	private String pergunta;
	
	
	
}
