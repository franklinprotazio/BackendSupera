package br.com.banco.integration.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banco.core.entity.Conta;
import br.com.banco.core.entity.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Modifying
	@Query(value = "DELETE FROM Transferencia as t WHERE t.conta_id = :conta_id", nativeQuery = true)
	void deletarTransferenciaPorConta(@Param("conta_id") Long idConta);

	@Query(value = "SELECT * FROM Transferencia as t WHERE t.conta_id = :id", nativeQuery = true)
	List<Transferencia> findTransferenciaByConta(@Param("id") Long idConta);

	List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);

	boolean deleteAllByConta(Conta conta);

	@Query("SELECT t FROM Transferencia t WHERE (:inicio IS NULL OR :inicio = '' OR t.dataTransferencia >= :inicio) "
			+ "AND (:fim IS NULL OR :fim = '' OR t.dataTransferencia <= :fim) "
			+ "AND (:nome IS NULL OR :nome = '' OR t.nomeOperadorTransacao = :nome)")
	List<Transferencia> getTransferencias(@Param("inicio") Date inicio, @Param("fim") Date fim,
			@Param("nome") String nome);

	@Query("SELECT t FROM Transferencia t WHERE (:inicio IS NULL OR :inicio = '' OR t.dataTransferencia >= TO_DATE(:inicio, 'yyyy-MM-dd')) "
			+ "AND (:fim IS NULL OR :fim = '' OR t.dataTransferencia <= TO_DATE(:fim, 'yyyy-MM-dd')) "
			+ "AND (:nome IS NULL OR :nome = '' OR t.nomeOperadorTransacao = :nome)")
	List<Transferencia> getTransferencias(@Param("inicio") String inicio, @Param("fim") String fim,
			@Param("nome") String nome);

}
