package com.aercio.springtestecrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    @ManyToOne
    @JoinColumn(name = "idConta")
    private Contas idConta;

    private Double valor;
    private Date dataTransacao;

}
