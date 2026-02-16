package edu.fatec.sigvs_cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fatec.sigvs_cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCpf(String cpf);

    Cliente findByEmail(String email);
}
