package br.com.exception.chapie.controller;

import br.com.exception.chapie.model.Execucao;
import br.com.exception.chapie.service.ExecucaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/execucoes")
public class ExecucaoController {

    @Autowired
    private ExecucaoService execucaoService;

    @GetMapping
    @ApiOperation(value = "Lista todas as Execuções")
    public ResponseEntity<List<Execucao>> findAll(){
        List<Execucao> execucoes = execucaoService.listarTodos();
        return ResponseEntity.ok().body(execucoes);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Lista uma determinada Execução por ID")
    public ResponseEntity<Execucao> findById(@PathVariable Integer id){
        Execucao execucao = execucaoService.encontrarPeloId(id);
        return ResponseEntity.ok().body(execucao);
    }

    @PostMapping()
    @ApiOperation(value = "Salva uma Execução")
    public ResponseEntity<Execucao> insert(@Valid @RequestBody Execucao execucao){
        Execucao execucaoUpdated = execucaoService.inserir(execucao);
        URI localizacao = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(execucaoUpdated.getId()).toUri();
        return ResponseEntity.created(localizacao).body(execucaoUpdated);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove uma Execução a partir de um ID")
    public ResponseEntity delete(@PathVariable Integer id){
        Execucao execucao = execucaoService.encontrarPeloId(id);
        execucaoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualiza uma determinada Execução")
    public ResponseEntity<Execucao> update(@Valid @RequestBody Execucao execucao,@PathVariable Integer id){
        Execucao execucaoUpdated = execucaoService.atualizar(execucao,id);
        return ResponseEntity.ok().body(execucaoUpdated);
    }

}
