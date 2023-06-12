package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoGenero;
import br.edu.femass.entities.Autor;
import br.edu.femass.entities.Genero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GeneroController implements Initializable {
    private DaoGenero dao = new DaoGenero(Genero.class);
    
    @FXML
    private TextField TxtGenero;

    @FXML
    private ListView<Genero> listaGenero;

    @FXML
    private void btnSalvar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try {
            if (TxtGenero.getText().length() == 0) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                Genero genero = new Genero(TxtGenero.getText());
                dao.create(genero);

                preencherLista();
            }
            
        } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtGenero.setText("");
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
        Genero genero = listaGenero.getSelectionModel().getSelectedItem();

        if (genero == null) return;
        try {
            dao.delete(genero.getId());
            TxtGenero.setText("");
        }  catch (Exception e){
            e.printStackTrace();
        }

        preencherLista();
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        TxtGenero.setText("");
    }

    @FXML
    private void listaAutor_mouseClicked(MouseEvent event){
        exibirDados();
    }

    @FXML
    private void listaAutor_keyPressed(KeyEvent event){
        exibirDados();
    }

    private void exibirDados() {
        Genero genero = listaGenero.getSelectionModel().getSelectedItem();
        if(genero == null) return;
        
        TxtGenero.setText(genero.getGenero());
    }

    private void preencherLista(){
        List<Genero> generos = dao.buscar();
        ObservableList<Genero> data = FXCollections.observableArrayList(generos);
        listaGenero.setItems(data);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherLista();
    }
}
