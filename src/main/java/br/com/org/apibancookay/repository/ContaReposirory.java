package br.com.org.apibancookay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.org.apibancookay.model.Conta;

@Repository
public interface ContaReposirory extends JpaRepository<Conta, Long> {
	Optional<Conta> findById(Long id);
}
