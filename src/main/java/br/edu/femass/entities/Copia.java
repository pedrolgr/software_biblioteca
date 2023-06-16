package br.edu.femass.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Copia{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean copiaFixa = false;
    private boolean copiaEmprestada = false;

    public Copia(String nome) {
        this.nome = nome;
    }

    public void setCopiaFixa(boolean valor) {
        this.copiaFixa = valor;
    }

    public boolean isCopiaFixa() {
        return copiaFixa;
    }

    public boolean isCopiaEmprestada(){
        return copiaEmprestada;
    }

    public void setCopiaEmprestada(boolean value){
        this.copiaEmprestada = value;
    }

    @Override
    public String toString() {
        if(copiaFixa) return Long.toString(getId()) + " *";

        return Long.toString(getId());
    }
}
