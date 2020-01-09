package com.aercio.springtestecrud.repository;

import com.aercio.springtestecrud.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
}
