package edu.fatec.sigvs_cliente.service;

import edu.fatec.sigvs_cliente.model.Cliente;

public interface IClienteService {
    public Cliente cadastrarCliente(Cliente cliente);

    public Cliente buscarClientePorId(Long id);

    public Cliente buscarClientePorCpf(String cpf);

    public Cliente buscarClientePorEmail(String email);

    public Cliente atualizarCliente(Cliente cliente);

    public void deletarCliente(Long id);
}
