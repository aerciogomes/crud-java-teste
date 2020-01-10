package com.aercio.springtestecrud.repository;

import com.aercio.springtestecrud.model.Contas;
import com.aercio.springtestecrud.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

    List findByIdContaAndDataTransacaoBetween(Long idConta, Date DataInicial, Date DataFinal);

    List findByIdConta(Long idConta);
}
