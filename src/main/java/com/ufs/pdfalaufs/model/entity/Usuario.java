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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario", schema = "audiencias")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id_usuario")
	private UUID idUsuario;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "ativo")
	private String ativo;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name  = "telefone_principal")
	private String telefonePrincipal;
	
	@Column(name = "senha")
	private String senha;
	
	@Builder.Default
	@Column(name = "dt_inc")
	@Temporal(TemporalType.TIMESTAMP)    
	private Date dtInc = new java.sql.Date(System.currentTimeMillis());
	
	@Column(name = "dt_alt")
	@Temporal(TemporalType.TIMESTAMP)    
	private Date datAlt;
	
}

