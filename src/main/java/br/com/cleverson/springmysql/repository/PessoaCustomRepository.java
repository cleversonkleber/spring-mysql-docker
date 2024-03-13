package br.com.cleverson.springmysql.repository;

import br.com.cleverson.springmysql.model.Pessoa;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaCustomRepository {

    private final EntityManager entityManager;

    public PessoaCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Pessoa> find(Long id, String nome, String sobreNome){
        String query = " select P from Pessoa as P ";
        String condicao = "where";

        if(id !=null){
            query += condicao + " P.id = :id ";
            condicao = " and ";
        }

        if(nome != null){
            query += condicao + " P.nome= :nome";
            condicao = " and ";
        }

        if(sobreNome != null){
            query += condicao + " P.sobrenome= :sobrenome";
        }

        var q = entityManager.createQuery(query,Pessoa.class);

        if(id !=null){
            q.setParameter("id",id);
        }
        if(nome !=null){
            q.setParameter("nome", nome);
        }
        if(sobreNome != null){
            q.setParameter("sobrenom", sobreNome);
        }


        return q.getResultList();


    }
}

