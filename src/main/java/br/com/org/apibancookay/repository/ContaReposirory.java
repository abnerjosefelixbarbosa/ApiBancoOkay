package br.com.org.apibancookay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.org.apibancookay.model.Conta;

@Repository
public interface ContaReposirory extends JpaRepository<Conta, Long> {
	boolean existsById(Long id);
	Optional<Conta> findById(Long id);
	boolean existsByAgenciaAndConta(String pAgencia, String pConta);
	Optional<Conta> findByAgenciaAndConta(String pAgencia, String pConta);
}
