package br.com.banco.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CONTA")
public class Conta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Long idConta;
	
	@Column(name = "nome_responsavel")
	private String nomeResponsavel;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "conta_id")
	private List<Transferencia> transferencias; 
	
}
