package com.aercio.springtestecrud.service;


import com.aercio.springtestecrud.model.Contas;
import com.aercio.springtestecrud.model.Pessoas;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
public class ServicoValidacao {

    public static boolean validaPessoa(Pessoas pessoa){
        if(pessoa.getNome() == null || pessoa.getNome().isEmpty()){return false;}
        if(pessoa.getCpf() == null || pessoa.getCpf().isEmpty()) {return false;}
        if(pessoa.getDataNascimento() == null) {return false;}

        return true;
    }

    public static boolean validaConta(Contas conta){
        if(conta.getLimiteSaqueDiario() == null){return false;}
        if(conta.getTipoConta() == null) {return false;}
        if(conta.getSaldo() == null) {return false;}
        if(conta.getFlagAtivo() == null){return false;}
        if(conta.getIdPessoa() == null) {return false;}
        conta.setDataCriacao(new Date());
        return true;
    }

    public static boolean validaSaqueConta(Contas conta, Double valorSaque){
        if(conta.getLimiteSaqueDiario() < valorSaque){return false;}
        if(conta.getSaldo() < valorSaque){return false;}
        if(conta.getFlagAtivo() == 0) {return false;}

        return true;
    }
}
