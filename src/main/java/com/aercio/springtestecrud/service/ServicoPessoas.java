package com.aercio.springtestecrud.service;


import com.aercio.springtestecrud.model.Pessoas;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServicoPessoas {
    public static boolean validaPessoa(Pessoas pessoa){
        if(pessoa.getNome() == null || pessoa.getNome().isEmpty()){return false;}
        if(pessoa.getCpf() == null || pessoa.getCpf().isEmpty()) {return false;}
        if(pessoa.getDataNascimento() == null) {return false;}

        return true;
    }
}
