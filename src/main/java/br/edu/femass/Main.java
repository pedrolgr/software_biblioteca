package br.edu.femass;

import java.util.List;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoCopia;
import br.edu.femass.entities.Autor;
import br.edu.femass.entities.Copia;
import br.edu.femass.entities.Leitor;
import br.edu.femass.entities.Telefone;

public class Main {
	public static void main(String[] args) {
        EntryPoint.main(args);
        // Autor autor = new Autor();

        // autor.setNome("Pedro");
        // autor.setSobreNome("Chavoso");

        // Dao<Autor> daoAutor = new Dao<>(Autor.class);

        // daoAutor.create(autor);

        // for (Leitor l: leitores) {
        //     System.out.println(l);
        // }


        // Copia copia = new Copia();
        // DaoCopia dao = new DaoCopia(Copia.class);

        // dao.create(copia);

    }
}
