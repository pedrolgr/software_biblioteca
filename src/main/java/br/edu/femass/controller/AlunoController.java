package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoLeitor;
import br.edu.femass.entities.Aluno;
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

public class AlunoController implements Initializable {

    private Dao<Aluno> dao = new Dao<>(Aluno.class);

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtMatricula;

    @FXML
    private ListView<Aluno> listaLeitor;

    @FXML
    private void btnSalvar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try {
            if (listaLeitor.getSelectionModel().getSelectedItem() == null) {
                if (TxtNome.getText().length() == 0 ||
                        TxtEmail.getText().length() == 0 ||
                        TxtTelefone.getText().length() == 0) {
                    throw new IllegalArgumentException("Preencha os campos obrigatórios");
                } else {
                    Aluno aluno = new Aluno(TxtNome.getText(), TxtEmail.getText(),
                            TxtTelefone.getText(), TxtMatricula.getText());
                    dao.create(aluno);
                }
            } else {
                Aluno aluno = listaLeitor.getSelectionModel().getSelectedItem();

                aluno.setNome(TxtNome.getText());
                aluno.setEmail(TxtEmail.getText());
                aluno.setTelefone(TxtTelefone.getText());
                aluno.setMatricula(TxtMatricula.getText());

                dao.update(aluno);
            }

        } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtMatricula.setText("");

        preencherLista();
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
        Aluno aluno = listaLeitor.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.ERROR);

        if (aluno == null)
            return;
        try {
            dao.delete(aluno.getId());
            TxtNome.setText("");
            TxtEmail.setText("");
            TxtTelefone.setText("");
            TxtMatricula.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        preencherLista();
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtMatricula.setText("");
    }

    @FXML
    private void listaLeitor_mouseClicked(MouseEvent event) {
        exibirDados();
    }

    @FXML
    private void listaLeitor_keyPressed(KeyEvent event) {
        exibirDados();
    }

    public void exibirDados() {
        Aluno aluno = listaLeitor.getSelectionModel().getSelectedItem();
        if (aluno == null)
            return;

        TxtNome.setText(aluno.getNome());
        TxtEmail.setText(aluno.getEmail());
        TxtTelefone.setText(aluno.getTelefone());
        TxtMatricula.setText(aluno.getMatricula());
    }

    public void preencherLista() {
        List<Aluno> alunos = dao.buscar();
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        listaLeitor.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherLista();
    }
}
