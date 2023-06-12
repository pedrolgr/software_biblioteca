package br.edu.femass.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer ano;
    private String edicao;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Genero genero;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Copia> copias = new ArrayList<>();

    public void addCopia(Copia copia) {
        copias.add(copia);
    }

    public Livro(String nome, Integer ano, String edicao, Genero genero) {
        this.nome = nome;
        this.ano = ano;
        this.edicao = edicao;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
