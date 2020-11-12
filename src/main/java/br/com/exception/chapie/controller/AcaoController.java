package br.com.exception.chapie.controller;

import br.com.exception.chapie.model.Acao;
import br.com.exception.chapie.service.AcaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/acoes")
public class AcaoController {

    @Autowired
    private AcaoService acaoService;

    @GetMapping
    @ApiOperation(value = "Encontrar todas as Ações")
    public ResponseEntity<List<Acao>> findAll(){
        List<Acao> acoes = acaoService.findAll();
        return ResponseEntity.ok().body(acoes);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Encontrar uma Ação por ID")
    public ResponseEntity<Acao> findById(@PathVariable Integer id){
        Acao acao = acaoService.findById(id);
        return ResponseEntity.ok().body(acao);
    }

    @PostMapping()
    @ApiOperation(value = "Salvar uma determinada Ação")
    public ResponseEntity<Acao> insert(@Valid @RequestBody Acao acao){
        Acao acaoUpdated = acaoService.insert(acao);
        URI localizacao = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("{/id}")
                            .buildAndExpand(acaoUpdated.getId()).toUri();
        return ResponseEntity.created(localizacao).body(acaoUpdated);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove uma Ação a partir de um determinado ID")
    public ResponseEntity delete(@PathVariable Integer id){
        Acao acao = acaoService.findById(id);
        acaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualiza uma determinada Ação")
    public ResponseEntity<Acao> update(@Valid @RequestBody Acao acao, @PathVariable Integer id){
        Acao acaoUpdated = acaoService.update(acao,id);
        return ResponseEntity.ok().body(acaoUpdated);
    }

}
