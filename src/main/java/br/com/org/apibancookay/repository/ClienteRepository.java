package br.com.org.apibancookay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.org.apibancookay.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByCpfAndSenhaCliente(String pcpf, String psenhaCliente);
	boolean existsByCpfAndSenhaCliente(String pcpf, String psenhaCliente);
}
