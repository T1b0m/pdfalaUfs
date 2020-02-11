package com.ufs.pdfalaufs.model.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.ufs.pdfalaufs.model.entity.Usuario.UsuarioBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Eixo", schema = "audiencias")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eixo {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id_eixo")
	private UUID idEixo;
	
	@Column(name = "logo")
	private String logo;
	
	@Column(name = "visivel")
	private String visivel;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "ativo")
	private String ativo;
	
	@Builder.Default
	@Column(name = "dt_inc")
	@Temporal(TemporalType.TIMESTAMP)    
	private Date dtInc = new java.sql.Date(System.currentTimeMillis());
	
	@Column(name = "dt_alt")
	@Temporal(TemporalType.TIMESTAMP)    
	private Date datAlt;
}