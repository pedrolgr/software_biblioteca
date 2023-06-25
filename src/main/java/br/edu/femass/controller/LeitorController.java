package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoLeitor;
import br.edu.femass.entities.Aluno;
import br.edu.femass.entities.Leitor;
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

public class LeitorController implements Initializable{
    private DaoLeitor dao = new DaoLeitor(Leitor.class);

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtFormacao;

    @FXML
    private TextField TxtMatricula;

    @FXML
    private ListView<Leitor> listaLeitor;

    @FXML
    private void btnSalvar(ActionEvent event) {
       Alert alerta = new Alert(AlertType.ERROR);

       try{
            if (TxtNome.getText().length() == 0 ||
            TxtEmail.getText().length() == 0 ||
            TxtTelefone.getText().length() == 0) {
                throw new IllegalArgumentException("Preencha os campos obrigatórios");
            } else {
                if (TxtFormacao.getText().length() != 0 && TxtMatricula.getText().length() != 0) {
                    throw new IllegalArgumentException("Os campos Formação e Matriculas não podem estar preenchidos juntos em um único cadastro.");
                } else {
                    if (TxtMatricula.getText().length() != 0) {
                        Aluno aluno = new Aluno(TxtNome.getText(), TxtEmail.getText(),
                        TxtTelefone.getText(), TxtMatricula.getText());
                        
                        dao.create(aluno);
                    } else if (TxtFormacao.getText().length() != 0) {
                        Professor professor = new Professor(TxtNome.getText(), TxtEmail.getText(),
                        TxtTelefone.getText(), TxtFormacao.getText());
                        
                        dao.create(professor);
                    }
                }
            }
       } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtMatricula.setText("");
        TxtFormacao.setText("");
        listaLeitor.getSelectionModel().clearSelection();

        preencherLista();
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
       
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtMatricula.setText("");
        TxtFormacao.setText("");
        listaLeitor.getSelectionModel().clearSelection();
    }

    @FXML
    private void listaLeitor_mouseClicked(MouseEvent event){
        exibirDados();
    }

    @FXML
    private void listaLeitor_keyPressed(KeyEvent event){
        exibirDados();
    }

    public void exibirDados() {
        Leitor leitor = listaLeitor.getSelectionModel().getSelectedItem();
        if (leitor == null) return;
        

        TxtNome.setText(leitor.getNome());
        TxtEmail.setText(leitor.getEmail());
        TxtTelefone.setText(leitor.getTelefone());
    }

    public void preencherLista() {
        List<Leitor> leitores = dao.buscar();
        ObservableList<Leitor> data = FXCollections.observableArrayList(leitores);
        listaLeitor.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherLista();
    }
}
