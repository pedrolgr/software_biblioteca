package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoLeitor;
import br.edu.femass.entities.Professor;
import br.edu.femass.entities.Autor;
import br.edu.femass.entities.Leitor;
import br.edu.femass.entities.Professor;
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

public class ProfessorController implements Initializable{

    private Dao<Professor> dao = new Dao<>(Professor.class);

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtFormacao;

    @FXML
    private ListView<Professor> listaLeitor;

    @FXML
    private void btnSalvar(ActionEvent event) {
       Alert alerta = new Alert(AlertType.ERROR);

       try{
            if (TxtNome.getText().length() == 0 ||
            TxtEmail.getText().length() == 0 ||
            TxtTelefone.getText().length() == 0) {
                throw new IllegalArgumentException("Preencha os campos obrigatórios");
            } else {
                Professor professor = new Professor(TxtNome.getText(), TxtEmail.getText(),
                TxtTelefone.getText(), TxtFormacao.getText());
                dao.create(professor);
                }
       } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtFormacao.setText("");

        preencherLista();
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
        Professor professor = listaLeitor.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.ERROR);

        if(professor == null) return;
        try {
            dao.delete(professor.getId());
            TxtNome.setText("");
            TxtEmail.setText("");
            TxtTelefone.setText("");
            TxtFormacao.setText("");
        } catch (Exception e){
            e.printStackTrace();
        }
        
        preencherLista();
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtFormacao.setText("");
    }

    @FXML
    private void listaLeitor_mouseClicked(MouseEvent event){
        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtFormacao.setText("");

        exibirDados();
    }

    @FXML
    private void listaLeitor_keyPressed(KeyEvent event){
        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtFormacao.setText("");
        
        exibirDados();
    }

    public void exibirDados() {
        Professor professor = listaLeitor.getSelectionModel().getSelectedItem();
        if (professor == null) return;

        TxtNome.setText(professor.getNome());
        TxtEmail.setText(professor.getEmail());
        TxtTelefone.setText(professor.getTelefone());
        TxtFormacao.setText(professor.getFormacao());
    }

    public void preencherLista() {
        List<Professor> professors = dao.buscar();
        ObservableList<Professor> data = FXCollections.observableArrayList(professors);
        listaLeitor.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherLista();
    }
}
