package edu.fatec.sigvs_cliente.service;

import org.springframework.stereotype.Service;

import edu.fatec.sigvs_cliente.model.Cliente;
import edu.fatec.sigvs_cliente.repository.ClienteRepository;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import java.net.ConnectException;

@Service
public class ClienteService implements IClienteService {
    Logger logger = LogManager.getLogger(this.getClass());
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
        logger.info(">>>>>> cliente cadastrado com sucesso...");
        try {
            publishEvent(c);
        } catch (Exception e) {
            logger.error(">>>>>> erro ao publicar evento de cliente: " + e.getMessage());
            throw new RuntimeException("Erro ao publicar evento de cliente");
        }
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

    @Override
    public String publishEvent(Cliente cliente) {
        logger.info(">>>>>> publishEvent de cliente iniciado...");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/v1/clientes";
        ResponseEntity<Cliente> response = restTemplate.postForEntity(url, cliente, Cliente.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Evento publicado com sucesso. Status: " + response.getStatusCode());
            return response.getStatusCode().toString();
        } else {
            logger.info("Falha ao publicar evento. Status: " + response.getStatusCode());
            throw new RuntimeException("Falha ao publicar evento. Status: " + response.getStatusCode());
        }
    }

}
