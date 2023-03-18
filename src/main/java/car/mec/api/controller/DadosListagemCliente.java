package car.mec.api.controller;

import car.mec.api.cliente.Cliente;

public record DadosListagemCliente(Long id, String nome, String telefone, String email) {

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail());
    }
}
