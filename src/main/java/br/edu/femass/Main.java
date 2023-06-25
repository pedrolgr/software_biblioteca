package br.edu.femass;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoGenero;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.entities.Autor;
import br.edu.femass.entities.Genero;
import br.edu.femass.entities.Livro;

public class Main {
	public static void main(String[] args) {
        // DaoGenero daoGenero = new DaoGenero(Genero.class);
        // DaoAutor daoAutor = new DaoAutor(Autor.class);
        // DaoLivro daoLivro = new DaoLivro(Livro.class);

        // Genero genero = new Genero("Romance");
        // Autor autor = new Autor("Paulo", "Fonseca");
        // Livro livro = new Livro("Narnia", 2005, "2", genero, autor);

        // daoGenero.create(genero);
        // daoAutor.create(autor);
        // daoLivro.create(livro);

        EntryPoint.main(args);
    }
}
