package edu.fatec.sigvs_cliente.service;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteRegistrationListener implements ApplicationListener<ClienteRegistrationEvent> {

    private java.util.List<ClienteRegistrationEvent> events = new java.util.ArrayList<>();

    @Override
    public void onApplicationEvent(ClienteRegistrationEvent event) {
        System.out.println(">>>>>>Cliente cadastrado: " + event.getCliente().getNome());
        events.add(event);
    }

    public java.util.List<ClienteRegistrationEvent> getEvents() {
        return events;
    }

    public void clearEvents() {
        events.clear();
    }
}
