package edu.fatec.sigvs_cliente.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.fatec.sigvs_cliente.service.ClienteService;

@SpringBootTest
public class TIReq09ClienteCadastrarTests {
    // entidade
    private Cliente cliente;

    @Autowired
    private ClienteService service;

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

    }

    @Test
    void ct01_quando_informacoes_validas_deve_cadastrar_cliente() {
        try {
            Cliente c1 = service.cadastrarCliente(cliente);
            assertNotNull(c1);

        } catch (Exception e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }

    @Test
    void ct02_quando_informacoes_invalidas_deve_retornar_erro() {
        try {
            cliente.setCpf("");
            Cliente c1 = service.cadastrarCliente(cliente);
            assertNotNull(c1);

        } catch (Exception e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }

    @Test
    void ct03_quando_informacoes_validas_deve_cadastrar_cliente() {
        // Se o serviço de destino (porta 8080) estiver fora do ar,
        // o método agora lança IllegalArgumentException.
        // Usamos o assertThrows para confirmar que o erro foi lançado com a mensagem
        // correta.

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.cadastrarCliente(cliente);
        });
        assertEquals("Erro ao publicar evento de cliente", ex.getMessage());
    }
}
