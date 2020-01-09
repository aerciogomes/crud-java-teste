package com.aercio.springtestecrud.controller;

import com.aercio.springtestecrud.model.Contas;
import com.aercio.springtestecrud.model.Pessoas;
import com.aercio.springtestecrud.repository.ContasRepository;
import com.aercio.springtestecrud.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/Contas"})
public class ContasController {

    @Autowired
    private ContasRepository repository;

    @Autowired
    private PessoasRepository pessoaRepository;

    @GetMapping
    public List<Contas> findAll(){
        return repository.findAll();
    }


    /*
        HTTP 200 (ResponseEntity.ok())
        HTTP 404 - Não Encontrado (ResponseEntity.notFound())
        TODO: findByIdConta ou findById????
     */
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdConta(@PathVariable long id){

        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contas createConta(@RequestBody Contas contas){
//        List a = pessoaRepository.findByIdPessoa(contas.getIdPessoa());
//        if(a.isEmpty()){
//            return "Não Encontrado";
//        }

        return repository.save(contas);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateConta(@PathVariable("id") long id, @RequestBody Contas contas){
        return repository.findById(id)
                .map(record -> {
                    record.setLimiteSaqueDiario(contas.getLimiteSaqueDiario());
                    record.setSaldo(contas.getSaldo());
                    record.setFlagAtivo(contas.getFlagAtivo());
                    record.setTipoConta(contas.getTipoConta());
                    record.setDataCriacao(contas.getDataCriacao());
                    record.setIdPessoa(contas.getIdPessoa());
                    Contas updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteConta(@PathVariable Long id){
        return repository.findById(id)
                .map(record ->{
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
