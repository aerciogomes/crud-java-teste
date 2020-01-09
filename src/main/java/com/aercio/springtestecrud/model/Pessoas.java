package com.aercio.springtestecrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pessoas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    private String nome;
    private String cpf;
    private Date dataNascimento;



//    public boolean validaPessoa(Pessoas pessoa){
//        if(pessoa.getNome() == null || pessoa.getNome().isEmpty()){return false;}
//        if(pessoa.getCpf() == null || pessoa.getCpf().isEmpty()) {return false;}
//        if(pessoa.getDataNascimento() == null) {return false;}
//
//        return true;
//    }
}
