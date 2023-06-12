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
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Leitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<>();

    public void addTelefone(Telefone telefone) {
        telefones.add(telefone);
    }

    @Override
    public String toString() {
        return "Leitor [id=" + id + ", nome=" + nome + ", email=" + email + ", telefones=" + telefones + "]";
    }

    
}
