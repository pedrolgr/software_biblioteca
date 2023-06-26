package br.edu.femass.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoCopia;
import br.edu.femass.dao.DaoLeitor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.entities.Aluno;
import br.edu.femass.entities.Copia;
import br.edu.femass.entities.Emprestimo;
import br.edu.femass.entities.Leitor;
import br.edu.femass.entities.Livro;
import br.edu.femass.entities.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.time.format.DateTimeFormatter;

public class EmprestimoController implements Initializable {

    private DaoLivro daoLivro = new DaoLivro(Livro.class);
    private DaoCopia daoCopia = new DaoCopia(Copia.class);
    private DaoLeitor daoLeitor = new DaoLeitor(Leitor.class);
    private Dao<Emprestimo> daoEmprestimo = new Dao<>(Emprestimo.class);

    @FXML
    private ComboBox<Leitor> ComboLeitor;

    @FXML
    private ListView<Copia> listaCopia;

    @FXML
    private ListView<Livro> listaLivro;

    @FXML
    ListView<Emprestimo> listaEmprestimo;

    @FXML
    private void btnEmprestimo(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);
        LocalDate dataAtual = LocalDate.now();
        try {
                if (ComboLeitor.getValue() == null ||
                        listaLivro.getSelectionModel().getSelectedItem() == null ||
                        listaCopia.getSelectionModel().getSelectedItem() == null) {
                    throw new IllegalArgumentException("Todos os campos são obrigatórios!");
                } else {
                    Leitor leitor = ComboLeitor.getSelectionModel().getSelectedItem();
                    Copia copia = listaCopia.getSelectionModel().getSelectedItem();
                    if (copia.isCopiaEmprestada()) 
                        throw new IllegalArgumentException("Copia Indisponivel!");
                    if (!copia.isCopiaFixa()) {
                        if (leitor instanceof Aluno) {
                            if (leitor.getCopias().size() < 5) {
                                copia.setCopiaEmprestada(true);
                                leitor.addCopia(copia);
                                Emprestimo emprestimo = new Emprestimo(dataAtual, dataAtual.plusDays(5), null, leitor,
                                        copia);

                                daoCopia.update(copia);
                                daoEmprestimo.create(emprestimo);
                            } else {
                                throw new IllegalArgumentException("O aluno possui 5 empréstimos!");
                            }

                        } else if (leitor instanceof Professor) {
                            if (leitor.getCopias().size() < 30) {
                                copia.setCopiaEmprestada(true);
                                leitor.addCopia(copia);
                                Emprestimo emprestimo = new Emprestimo(dataAtual, dataAtual.plusDays(30), null, leitor,
                                        copia);

                                daoCopia.update(copia);
                                daoEmprestimo.create(emprestimo);
                            } else {
                                throw new IllegalArgumentException("O aluno possui 5 empréstimos!");
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Essa cópia não pode ser emprestada!");
                    }
                }

        } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        ComboLeitor.valueProperty().set(null);
        listaCopia.getItems().clear();
        listaLivro.getSelectionModel().clearSelection();
        listaEmprestimo.getSelectionModel().clearSelection();
        preencherListaLivro();
        preencherListaCopia();
        preencherListaEmprestimo();
    }

    @FXML
    private void btnDevolverEmprestimo(ActionEvent event) {
        Emprestimo emprestimo = listaEmprestimo.getSelectionModel().getSelectedItem();
        Leitor leitor = emprestimo.getLeitor();

        if (emprestimo == null)
            return;

        LocalDate dataAtual = LocalDate.now();
        emprestimo.setDataEntrega(dataAtual);
        Copia copia = emprestimo.getCopia();
        copia.setCopiaEmprestada(false);
        leitor.removeCopia(copia);
        daoCopia.update(copia);
        daoEmprestimo.update(emprestimo);

        
        listaCopia.getSelectionModel().clearSelection();
        listaLivro.getSelectionModel().clearSelection();
        ComboLeitor.valueProperty().set(null);
        listaEmprestimo.getSelectionModel().clearSelection();
        preencherListaCopia();
        preencherListaEmprestimo();
    }

    @FXML
    private void listaEmprestimo_mouseClicked(MouseEvent event) {
        preencherListaCopia();
    }

    @FXML
    private void listaEmprestimo_keyPressed(KeyEvent event) {
        preencherListaCopia();
    }

    @FXML
    private void listaLivro_mouseClicked(MouseEvent event) {
        preencherListaCopia();
    }

    @FXML
    private void listaLivro_keyPressed(KeyEvent event) {
        preencherListaCopia();
    }

    @FXML
    private void listaCopia_mouseClicked(MouseEvent event) {
        preencherListaCopia();
    }

    @FXML
    private void listaCopia_keyPressed(KeyEvent event) {
        preencherListaCopia();
    }

    public void preencherListaCopia() {
        Livro livro = listaLivro.getSelectionModel().getSelectedItem();
        if (livro == null)
            return;

        ObservableList<Copia> data = FXCollections.observableArrayList(livro.getCopias());
        listaCopia.setItems(data);
    }

    public void preencherListaEmprestimo() {
        List<Emprestimo> emprestimos = daoEmprestimo.buscar();
        ObservableList<Emprestimo> data = FXCollections.observableArrayList(emprestimos);
        listaEmprestimo.setItems(data);
    }

    public void preencherListaLivro() {
        List<Livro> livros = daoLivro.buscar();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        listaLivro.setItems(data);
    }

    public void exibirLeitores() {
        try {
            ObservableList<Leitor> data = FXCollections.observableArrayList(
                    daoLeitor.buscar());
            ComboLeitor.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherListaLivro();
        preencherListaEmprestimo();
        exibirLeitores();
    }

}
