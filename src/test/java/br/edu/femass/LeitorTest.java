package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.edu.femass.entities.Copia;
import br.edu.femass.entities.Leitor;
import br.edu.femass.entities.Telefone;

public class LeitorTest {
    @Test
    public void testConstructorAndGetters() {
        String nome = "Pedrin";
        String email = "pedrin@pedrin.com";
        String telefone = "123456789";

        Leitor leitor = new Leitor(nome, email, telefone);

        assertEquals(nome, leitor.getNome());
        assertEquals(email, leitor.getEmail());
        assertEquals(telefone, leitor.getTelefone());
    }

    @Test
    public void testAddCopia() {
        Leitor leitor = new Leitor("", "", "");
        Copia copia = new Copia("Copia 1");

        leitor.addCopia(copia);

        assertEquals(1, leitor.getCopias().size());
        assertEquals(copia, leitor.getCopias().get(0));
    }

}
