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
import jakarta.persistence.OneToOne;
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

    @ManyToOne(cascade = CascadeType.MERGE)
    private Autor autor;

    public void addCopia(Copia copia) {
        copias.add(copia);
    }

    public List<Copia> getCopiasLivres() {
        List<Copia> copiasEmprestimo = new ArrayList<>();

        for(Copia copia: this.copias){
            if(copia.isCopiaEmprestada() == false)
                copiasEmprestimo.add(copia);
        }

        return copiasEmprestimo;
    }

    public Livro(String nome, Integer ano, String edicao, Genero genero, Autor autor) {
        this.nome = nome;
        this.ano = ano;
        this.edicao = edicao;
        this.genero = genero;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return getNome() + "   -   ED " + getEdicao() ;
    }
}
