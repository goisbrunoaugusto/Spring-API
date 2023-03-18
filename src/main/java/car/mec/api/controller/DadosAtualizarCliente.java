package car.mec.api.controller;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCliente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        EnderecoRecord endereco) {
}
