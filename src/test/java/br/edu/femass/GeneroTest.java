package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.edu.femass.entities.Genero;

public class GeneroTest {

   @Test
    public void testConstructorAndGetters() {
        String genero = "Ação";
        Genero g = new Genero(genero);
        assertEquals(genero, g.getGenero());
    }

    @Test
    public void testSetter() {
        String genero = "Aventura";
        Genero g = new Genero();
        g.setGenero(genero);
        assertEquals(genero, g.getGenero());
    }
    
}
