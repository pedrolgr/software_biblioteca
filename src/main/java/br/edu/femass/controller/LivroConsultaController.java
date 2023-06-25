package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoCopia;
import br.edu.femass.dao.DaoGenero;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.entities.Autor;
import br.edu.femass.entities.Copia;
import br.edu.femass.entities.Emprestimo;
import br.edu.femass.entities.Genero;
import br.edu.femass.entities.Livro;
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

public class LivroConsultaController implements Initializable{
    private DaoLivro dao = new DaoLivro(Livro.class);
   
    @FXML
    private ListView<Livro> listaLivro;

    @FXML
    private ListView<Copia> listaCopia;

    public void preencherListaCopia() {
        Livro livro = listaLivro.getSelectionModel().getSelectedItem();
        if(livro == null) return;
        
        ObservableList<Copia> data = FXCollections.observableArrayList(livro.getCopiasLivres());
        listaCopia.setItems(data);
    }

    public void preencherListaLivro() {
        List<Livro> livros = dao.buscar();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        listaLivro.setItems(data);
    }

    @FXML
    private void listaAutor_mouseClicked(MouseEvent event){
       preencherListaCopia();
    }

    @FXML
    private void listaAutor_keyPressed(KeyEvent event){
        preencherListaCopia();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherListaLivro();
    }
}
