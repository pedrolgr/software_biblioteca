package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.entities.Genero;
import br.edu.femass.entities.Leitor;

public class DaoLeitor extends Dao<Leitor>{

    public DaoLeitor(Class<Leitor> entity) {
        super(entity);
    }

    public List<Leitor> buscar(){
        return em.createQuery("select l from Leitor l order by l.nome").getResultList();
    }
    
}
