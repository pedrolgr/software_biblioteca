package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.entities.Copia;
import br.edu.femass.entities.Livro;

public class DaoLivro extends Dao<Livro>{
    
    public DaoLivro(Class<Livro> entity) {
        super(entity);
    }

    public List<Livro> buscar(){
        return em.createQuery("select l from Livro l order by l.nome").getResultList();
    }
}
