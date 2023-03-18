package car.mec.api.controller;

import car.mec.api.cliente.Cliente;
import car.mec.api.cliente.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cliente") //url /cliente
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCliente dados){
        repository.save(new Cliente(dados));

    }

    @GetMapping
    public Page<DadosListagemCliente> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao){
       return repository.findAll(paginacao).map(DadosListagemCliente::new); //transforma DadosClienteListagem
        // em um uma lista de Cliente e ordena a paginacao na hora de imprimir a lista
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarCliente dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }
}
