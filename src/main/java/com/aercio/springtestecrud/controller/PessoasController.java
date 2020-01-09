package com.aercio.springtestecrud.controller;

import com.aercio.springtestecrud.model.Pessoas;
import com.aercio.springtestecrud.repository.PessoasRepository;
import com.aercio.springtestecrud.service.ServicoPessoas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/Pessoas"})
public class PessoasController {

    @Autowired
    private PessoasRepository repository;

    @GetMapping
    public List findAll(){

        System.out.print(repository.findByCpf("12345678923").toString());
        System.out.println(repository.findAll().toString());

        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createPessoa(@RequestBody Pessoas pessoa){
        if(ServicoPessoas.validaPessoa(pessoa)) {
            return ResponseEntity.ok().body(repository.save(pessoa));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updatePessoa(@PathVariable("id") long id, @RequestBody Pessoas pessoa){
        return repository.findById(id)
                .map(record -> {
                    if(pessoa.getNome() != null && !pessoa.getNome().isEmpty()) {record.setNome(pessoa.getNome());}
                    if(pessoa.getCpf() != null && !pessoa.getCpf().isEmpty()) {record.setCpf(pessoa.getCpf());}
                    if(pessoa.getDataNascimento() != null) {record.setDataNascimento(pessoa.getDataNascimento());}
                    Pessoas updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deletePessoa(@PathVariable Long id){
        return repository.findById(id)
                .map(record ->{
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
