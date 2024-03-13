package br.com.cleverson.springmysql.repository;


import br.com.cleverson.springmysql.controller.dto.PessoaRs;
import br.com.cleverson.springmysql.model.Pessoa;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByNomeContains(String name);

}

