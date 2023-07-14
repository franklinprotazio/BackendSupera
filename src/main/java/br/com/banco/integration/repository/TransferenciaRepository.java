package br.com.banco.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banco.core.entity.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

//	@Query("DELETE FROM Transferencia t WHERE t.conta = :idContaParaDeletar")
//	void deletarTransferenciaPorConta(@Param("idContaParaDeletar") Long idConta);
	
	@Modifying
	@Query(value = "DELETE FROM Transferencia as t WHERE t.conta_id = :conta", nativeQuery = true)
	void deletarTransferenciaPorConta(@Param("conta") Long id);


	@Query(value = "SELECT * FROM Transferencia as t WHERE t.conta_id = :id", nativeQuery = true)
//	List<Transferencia> findTransferenciaByConta();
	List<Transferencia> findTransferenciaByConta(@Param("id") Long idConta);

}

//@Query(value = "SELECT sc FROM SolicitacaoImportacao sc "
//		+ "WHERE (sc.codigoSituacaoSolicitacao = 2 OR sc.codigoSituacaoSolicitacao = 5 OR sc.codigoSituacaoSolicitacao = 4 "
//		+ "OR sc.codigoSituacaoSolicitacao = 7) " + "ORDER BY sc.sequencial", nativeQuery = true)