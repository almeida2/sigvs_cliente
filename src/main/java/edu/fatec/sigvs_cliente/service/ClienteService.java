package edu.fatec.sigvs_cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import edu.fatec.sigvs_cliente.model.Cliente;
import edu.fatec.sigvs_cliente.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        if (clienteRepository.findByCpf(cliente.getCpf()) != null) {
            throw new IllegalArgumentException("CPF ja cadastrado");
        }
        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        cliente.setDataCadastro();
        Cliente c = clienteRepository.save(cliente);
        eventPublisher.publishEvent(new ClienteRegistrationEvent(this, cliente));
        return c;
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public Cliente buscarClientePorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
