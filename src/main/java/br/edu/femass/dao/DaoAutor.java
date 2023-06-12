package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.entities.Autor;

public class DaoAutor extends Dao<Autor>{

    public DaoAutor(Class<Autor> entity) {
        super(entity);
    }

    public List<Autor> buscar(){
        return em.createQuery("select a from Autor a order by a.nome").getResultList();
    }
    
}
