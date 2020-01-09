package com.aercio.springtestecrud.repository;

import com.aercio.springtestecrud.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {

    List findByCpf(String cpf);

    List findByIdPessoa(Pessoas idPessoa);
}
