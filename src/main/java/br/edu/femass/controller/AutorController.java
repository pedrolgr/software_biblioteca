package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.entities.Autor;
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

public class AutorController implements Initializable{

    private DaoAutor dao = new DaoAutor(Autor.class);
    
    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtSobrenome;

    @FXML
    private ListView<Autor> listaAutor;

    @FXML
    private void btnSalvar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try {
            if (listaAutor.getSelectionModel().getSelectedItem() == null) {
                if (TxtNome.getText().length() == 0 || TxtSobrenome.getText().length() == 0) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                Autor autor = new Autor(TxtNome.getText(), TxtSobrenome.getText());
                dao.create(autor); 
            }
            } else {
                Autor autor = listaAutor.getSelectionModel().getSelectedItem();

                autor.setNome(TxtNome.getText());
                autor.setSobreNome(TxtSobrenome.getText());

                dao.update(autor);
            }
            
            
        } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtNome.setText("");
        TxtSobrenome.setText("");

        preencherLista();
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
        Autor autor = listaAutor.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.ERROR);

        if(autor == null) return;
        try {
            dao.delete(autor.getId());
            TxtNome.setText("");
            TxtSobrenome.setText("");
        } catch (Exception e){
            e.printStackTrace();
        }
        
        preencherLista();
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        TxtNome.setText("");
        TxtSobrenome.setText("");
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
        Autor autor = listaAutor.getSelectionModel().getSelectedItem();
        if(autor == null) return;
        
        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobreNome().toString());
    }

    private void preencherLista(){
        List<Autor> autores = dao.buscar();
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        listaAutor.setItems(data);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherLista();
    }

    
    
}
