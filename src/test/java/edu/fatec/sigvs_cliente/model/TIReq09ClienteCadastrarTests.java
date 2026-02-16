package edu.fatec.sigvs_cliente.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.fatec.sigvs_cliente.service.ClienteRegistrationEvent;
import edu.fatec.sigvs_cliente.service.ClienteRegistrationListener;
import edu.fatec.sigvs_cliente.service.ClienteService;

@SpringBootTest
public class TIReq09ClienteCadastrarTests {
    // entidade
    private Cliente cliente;

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteRegistrationListener listener;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNome("Joao");
        cliente.setCpf("97521069005");
        cliente.setEmail("joao@gmail.com");
        cliente.setCep("01001000");
        cliente.setEndereco("Praça da Sé");
        cliente.setBairro("Sé");
        cliente.setCidade("São Paulo");
        cliente.setComplemento("1");
        cliente.setDataCadastro();
        listener.clearEvents();

    }

    @Test
    void ct01_quando_informacoes_validas_deve_cadastrar_cliente() {
        try {
            // A geração do evento ocorre no serviço, não no repositório
            Cliente c1 = service.cadastrarCliente(cliente);
            assertNotNull(c1);

            // Valida se o evento foi disparado e capturado pelo listener
            assertEquals(1, listener.getEvents().size());
            assertEquals(cliente.getCpf(), listener.getEvents().get(0).getCliente().getCpf());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
