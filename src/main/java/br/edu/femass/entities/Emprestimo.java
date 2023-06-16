package br.edu.femass.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.CascadeType;
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
public class Emprestimo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalDate dataPrevista;
    private LocalDate dataEntrega;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Leitor leitor;
    @OneToOne
    private Copia copia;

    public Emprestimo(LocalDate data, LocalDate dataPrevista, 
    LocalDate dataEntrega, Leitor leitor, Copia copia) {
        this.data = data;
        this.dataPrevista = dataPrevista;
        this.dataEntrega = null;
        this.leitor = leitor;
        this.copia = copia;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataEmprestimo = data.format(formatter);
        String dataPrevista = getDataPrevista().format(formatter);
        if(dataEntrega == null) {
            return "[PENDENTE]" + leitor.getNome() + "   -   [DT Emprestimo: " + dataEmprestimo + "]   -   [DT Entrega Prevista: " + dataPrevista + "]" +  "   -   [DT Entrega: - ]";
        } else {
            String dataEntrega = getDataEntrega().format(formatter);
            return "[ENTREGUE]" + leitor.getNome() + "   -   [DT Emprestimo: " + dataEmprestimo + "]   -   [DT Entrega Prevista: " + dataPrevista + "]" +  "   -   [DT Entrega: " + dataEntrega + "]";
        }
    }

   
}
