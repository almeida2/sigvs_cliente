package edu.fatec.sigvs_cliente.service;

import org.springframework.context.ApplicationEvent;

import edu.fatec.sigvs_cliente.model.Cliente;

/**
 * A classe ClienteRegistrationEvent é um evento que será disparado quando um
 * cliente for cadastrado.
 * O metodo construtor recebe como parametro o source e o cliente.
 * O evento será ouvido pelo método onApplicationEvent do método
 * ClienteRegistrationListener.
 */
public class ClienteRegistrationEvent extends ApplicationEvent {
    private Cliente cliente;

    public ClienteRegistrationEvent(Object source, Cliente cliente) {
        super(source);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
