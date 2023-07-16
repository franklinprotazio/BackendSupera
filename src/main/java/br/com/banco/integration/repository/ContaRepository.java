package br.com.banco.integration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.core.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	Optional<Conta> findById(Long id);
	
}
