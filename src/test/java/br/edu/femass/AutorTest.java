package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.edu.femass.entities.Autor;

public class AutorTest {
    
    @Test
    public void testConstructorAndGetters() {
        String nome = "John";
        String sobreNome = "Doe";
        Autor autor = new Autor(nome, sobreNome);
        
        assertEquals(nome, autor.getNome());
        assertEquals(sobreNome, autor.getSobreNome());
    }

    @Test
    public void testSetter() {
        String nome = "Jane";
        String sobreNome = "Smith";
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setSobreNome(sobreNome);
        
        assertEquals(nome, autor.getNome());
        assertEquals(sobreNome, autor.getSobreNome());
    }
}
