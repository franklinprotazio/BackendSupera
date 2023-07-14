package br.com.banco.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TRANSFERENCIA")
public class Transferencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "data_transferencia")
	private Date dataTransferencia;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "nome_operador_transacao")
	private String nomeOperadorTransacao;

//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_conta", referencedColumnName = "id_conta")
//	private Conta conta;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
//	private Conta conta;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "conta_id", referencedColumnName = "id_conta", nullable = false)
//	@JsonIgnore
//	private Conta conta;
	
	@Column(name = "conta_id")
	private Long idConta;

}
