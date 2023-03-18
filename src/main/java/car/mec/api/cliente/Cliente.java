package car.mec.api.cliente;

import car.mec.api.controller.DadosAtualizarCliente;
import car.mec.api.controller.DadosCadastroCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente") //Em qual tabela essa entidade esta sendo mapeada no MySql
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Cliente(DadosCadastroCliente dados) {
        this.nome=dados.nome();
        this.email=dados.email();
        this.telefone = dados.telefone();
        this.endereco= new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizarCliente dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.email() != null){
            this.email = dados.email();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarEndereco(dados.endereco());
        }
    }
}
