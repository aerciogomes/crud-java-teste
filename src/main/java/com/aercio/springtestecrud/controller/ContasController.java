package com.aercio.springtestecrud.controller;

import com.aercio.springtestecrud.model.Contas;
import com.aercio.springtestecrud.model.Pessoas;
import com.aercio.springtestecrud.repository.ContasRepository;
import com.aercio.springtestecrud.repository.PessoasRepository;
import com.aercio.springtestecrud.service.ServicoValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
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
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdConta(@PathVariable Long id){

        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createConta(@RequestBody Contas contas){
        if(!ServicoValidacao.validaConta(contas)){
            return ResponseEntity.badRequest().build();
        }
        List a = pessoaRepository.findByIdPessoa(contas.getIdPessoa());
        if(a.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        contas.setDataCriacao(new Date(new java.util.Date().getTime()));
        return ResponseEntity.ok().body(repository.save(contas));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateConta(@PathVariable("id") Long id, @RequestBody Contas contas){
        return repository.findById(id)
                .map(record -> {
                    if(contas.getLimiteSaqueDiario() != null) record.setLimiteSaqueDiario(contas.getLimiteSaqueDiario());
                    if(contas.getSaldo() != null){record.setSaldo(contas.getSaldo());}
                    if(contas.getFlagAtivo() != null) {record.setFlagAtivo(contas.getFlagAtivo());}
                    if(contas.getTipoConta() != null) {record.setTipoConta(contas.getTipoConta());}
                    //if(contas.getDataCriacao() != null) {record.setDataCriacao(contas.getDataCriacao());}
                    if(contas.getIdPessoa() != null) {record.setIdPessoa(contas.getIdPessoa());}
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

//    @PutMapping(path = {"/deposito/{id}/valor/{deposito}"})
//    public ResponseEntity depositaConta(@PathVariable("id") Long id,@PathVariable Double deposito){
//        Contas conta = repository.findByIdConta(id);
//        Double Valor = conta.getSaldo() + deposito;
//        conta.setSaldo(Valor);
//        return updateConta(id,conta);
//    }
//
//    @PutMapping(path = {"/saque/{id}/{valor}"})
//    public ResponseEntity saqueConta(@PathVariable("id") Long id, @PathVariable("valor") Double valor){
//        Contas conta = repository.findByIdConta(id);
//        if(ServicoValidacao.validaSaqueConta(conta,valor)){
//            Double Valor = conta.getSaldo() - valor;
//            conta.setSaldo(Valor);
//            return updateConta(id,conta);
//        }else{
//            return ResponseEntity.noContent().build();
//        }
//    }

}
