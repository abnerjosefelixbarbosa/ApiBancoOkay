package br.com.org.apibancookay.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "telefone")
public class Telefone implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Column(length = 30, nullable = false, unique = true)
	private String telefone;
	@OneToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false, unique = true)
	private Cliente cliente;

}
