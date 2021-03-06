package com.aercio.springtestecrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConta;

//    @ManyToOne
//    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private Pessoas idPessoa;

    private Long idPessoa;

    private Double limiteSaqueDiario;
    private Double saldo;
    private Long flagAtivo;
    private Long tipoConta;
    private Date dataCriacao;

}
