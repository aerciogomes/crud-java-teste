package com.aercio.springtestecrud.controller;

import com.aercio.springtestecrud.model.Transacoes;
import com.aercio.springtestecrud.repository.TransacoesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/Transacoes"})
public class TransacoesController {

    private TransacoesRepository repository;

    TransacoesController(TransacoesRepository RepositorioTransacoes){
        
        this.repository = RepositorioTransacoes;
    }

    @GetMapping
    public List<Transacoes> findAll(){
        return repository.findAll();
    }

    
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdTransacao(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transacoes createTransacao(@RequestBody Transacoes transacao){
        return repository.save(transacao);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateTransacao(@PathVariable("id") long id, @RequestBody Transacoes transacao){
        return repository.findById(id)
                .map(record -> {
                    record.setIdConta(transacao.getIdConta());
                    record.setValor(transacao.getValor());
                    record.setDataTransacao(transacao.getDataTransacao());
                    Transacoes updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteTransacao(@PathVariable Long id){
        return repository.findById(id)
                .map(record ->{
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
