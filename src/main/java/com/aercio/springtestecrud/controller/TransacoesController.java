package com.aercio.springtestecrud.controller;

import com.aercio.springtestecrud.model.Contas;
import com.aercio.springtestecrud.model.Transacoes;
import com.aercio.springtestecrud.repository.ContasRepository;
import com.aercio.springtestecrud.repository.TransacoesRepository;
import com.aercio.springtestecrud.service.ServicoValidacao;
import com.aercio.springtestecrud.service.UtilitariosData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"/Transacoes"})
public class TransacoesController {

    @Autowired
    private TransacoesRepository repository;

    @Autowired
    private ContasRepository contasRepository;


    @GetMapping
    public List<Transacoes> findAll(){
        return repository.findAll();
    }

    
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdTransacao(@PathVariable Long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = {"/depositar"})
    public ResponseEntity createDeposito(@RequestBody Transacoes transacao){
        Contas conta = contasRepository.findByIdConta(transacao.getIdConta());
        if(!ServicoValidacao.validaTransacao(transacao) || Objects.isNull(conta)){
            return ResponseEntity.badRequest().build();
        }
        transacao.setDataTransacao(new Date(new java.util.Date().getTime()));
        conta.setSaldo(conta.getSaldo() + transacao.getValor());
        return ResponseEntity.ok().body(repository.save(transacao));
    }

    @PostMapping(path = {"/sacar"})
    public ResponseEntity createSaque(@RequestBody Transacoes transacao){
        Contas conta = contasRepository.findByIdConta(transacao.getIdConta());
        if(!ServicoValidacao.validaTransacao(transacao) || Objects.isNull(conta)){
            return ResponseEntity.badRequest().build();
        }
        if(!ServicoValidacao.validaSaqueConta(conta,transacao.getValor())){
            return ResponseEntity.badRequest().build();
        }
//        if(repository.findByIdContaAndDataTransacaoBetween(transacao.getIdConta(),))
        transacao.setDataTransacao(new Date(new java.util.Date().getTime()));
        conta.setSaldo(conta.getSaldo() - transacao.getValor());
        return ResponseEntity.ok().body(repository.save(transacao));
    }

    @GetMapping(path = {"/extrato/{id}"})
    public ResponseEntity extratoConta(@PathVariable Long id, @RequestBody UtilitariosData data){
        if(Objects.isNull(contasRepository.findByIdConta(id)) || data.getDataInicial() == null || data.getDataFinal() == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(repository.findByIdContaAndDataTransacaoBetween(id,data.getDataInicial(),data.getDataFinal()));
    }

    @GetMapping(path = {"/extratoTotal/{id}"})
    public ResponseEntity extratoTotal(@PathVariable Long id){
        if(Objects.isNull(contasRepository.findByIdConta(id))){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(repository.findByIdConta(id));
    }
}
