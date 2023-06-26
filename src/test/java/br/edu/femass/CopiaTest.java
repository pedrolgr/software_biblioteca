package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.edu.femass.entities.Copia;


public class CopiaTest {
    
    @Test
    public void testConstructorAndGetters() {
        String nome = "Copia 1";
        Copia copia = new Copia(nome);

        assertEquals(nome, copia.getNome());
        assertEquals(false, copia.isCopiaFixa());
        assertEquals(false, copia.isCopiaEmprestada());
    }

    @Test
    public void testSetCopiaFixa() {
        Copia copia = new Copia();
        copia.setCopiaFixa(true);

        assertEquals(true, copia.isCopiaFixa());
    }

    @Test
    public void testSetCopiaEmprestada() {
        Copia copia = new Copia();
        copia.setCopiaEmprestada(true);

        assertEquals(true, copia.isCopiaEmprestada());
    }

}
