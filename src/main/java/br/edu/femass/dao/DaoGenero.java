package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.entities.Autor;
import br.edu.femass.entities.Genero;

public class DaoGenero extends Dao<Genero>{

    public DaoGenero(Class<Genero> entity) {
        super(entity);
    }

    public List<Genero> buscar(){
        return em.createQuery("select g from Genero g order by g.genero").getResultList();
    }
}
