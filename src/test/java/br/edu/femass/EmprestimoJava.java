package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Test;

import br.edu.femass.entities.Copia;
import br.edu.femass.entities.Emprestimo;
import br.edu.femass.entities.Leitor;

public class EmprestimoJava {

    @Test
    public void testConstructorAndGetters() {
        LocalDate data = LocalDate.now();
        LocalDate dataPrevista = LocalDate.now().plusDays(7);
        Leitor leitor = new Leitor("", "", "");
        Copia copia = new Copia("Copia 1");

        Emprestimo emprestimo = new Emprestimo(data, dataPrevista, null, leitor, copia);

        assertEquals(data, emprestimo.getData());
        assertEquals(dataPrevista, emprestimo.getDataPrevista());
        assertNull(emprestimo.getDataEntrega());
        assertEquals(leitor, emprestimo.getLeitor());
        assertEquals(copia, emprestimo.getCopia());
    }

    @Test
    public void testToStringPendente() {
        LocalDate data = LocalDate.of(2023, 6, 1);
        LocalDate dataPrevista = LocalDate.of(2023, 6, 8);
        Leitor leitor = new Leitor("John Doe", "", "");
        Copia copia = new Copia("Copia 1");

        Emprestimo emprestimo = new Emprestimo(data, dataPrevista, null, leitor, copia);

        String expectedToString = "[PENDENTE]John Doe   -   [DT Emprestimo: 01/06/2023]   -   [DT Entrega Prevista: 08/06/2023]   -   [DT Entrega: - ]";

        assertEquals(expectedToString, emprestimo.toString());
    }
}
