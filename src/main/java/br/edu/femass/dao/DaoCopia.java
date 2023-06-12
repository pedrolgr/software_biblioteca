package br.edu.femass.dao;

import java.util.List;
import br.edu.femass.entities.Copia;

public class DaoCopia extends Dao<Copia>{

    public DaoCopia(Class<Copia> entity) {
        super(entity);
    }

    public List<Copia> buscar(){
        return em.createQuery("select c from Copia c order by c.nome").getResultList();
    }
}
