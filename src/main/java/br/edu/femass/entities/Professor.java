package br.edu.femass.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Professor extends Leitor{
    private String formacao;

    public Professor(String nome, String email, String telefone, String formacao) {
        super(nome, email, telefone);
        this.formacao = formacao;
    }

    @Override
    public String toString() {
        return getNome() + " [PROFESSOR]";
    }
}
