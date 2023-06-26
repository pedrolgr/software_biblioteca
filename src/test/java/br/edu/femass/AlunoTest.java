package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.edu.femass.entities.Aluno;
import br.edu.femass.entities.Autor;

public class AlunoTest {
    
    @Test
    public void testConstructorAndGetters() {
        String nome = "John Doe";
        String email = "john.doe@example.com";
        String telefone = "123456789";
        String matricula = "20210001";

        Aluno aluno = new Aluno(nome, email, telefone, matricula);

        assertEquals(nome, aluno.getNome());
        assertEquals(email, aluno.getEmail());
        assertEquals(telefone, aluno.getTelefone());
        assertEquals(matricula, aluno.getMatricula());
    }

    @Test
    public void testToString() {
        String nome = "Jane Smith";
        String matricula = "20210002";

        Aluno aluno = new Aluno(nome, "", "", matricula);
        String expectedToString = nome + " [ALUNO]";

        assertEquals(expectedToString, aluno.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        String nome = "Bob Johnson";
        String matricula = "20210003";

        Aluno aluno1 = new Aluno(nome, "", "", matricula);
        Aluno aluno2 = new Aluno(nome, "", "", matricula);

        assertEquals(aluno1, aluno2);
        assertEquals(aluno1.hashCode(), aluno2.hashCode());
    }
}