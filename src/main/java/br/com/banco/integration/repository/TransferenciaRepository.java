package br.com.banco.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banco.core.entity.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Modifying
	@Query(value = "DELETE FROM Transferencia as t WHERE t.conta_id = :conta", nativeQuery = true)
	void deletarTransferenciaPorConta(@Param("conta") Long id);

	@Query(value = "SELECT * FROM Transferencia as t WHERE t.conta_id = :id", nativeQuery = true)
	List<Transferencia> findTransferenciaByConta(@Param("id") Long idConta);
	
	@Modifying
	@Query(value = "DELETE FROM Transferencia as t WHERE t.id = :conta", nativeQuery = true)
	void deletarTransferenciaPorIsa(@Param("conta") Long id);

}

