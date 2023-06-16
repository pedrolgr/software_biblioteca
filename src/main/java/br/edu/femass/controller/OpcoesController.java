package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpcoesController implements Initializable{

    @FXML
    private void btnCadastrarLivro(ActionEvent action) throws IOException{
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroLivro.fxml"));
       
        try {
            Scene s = new Scene((Parent) fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastrar Livro");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCadastrarAutor(ActionEvent action) throws IOException{
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroAutor.fxml"));
       
        try {
            Scene s = new Scene((Parent) fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastrar Autor");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCadastrarGenero(ActionEvent action) throws IOException{
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroGenero.fxml"));
       
        try {
            Scene s = new Scene((Parent) fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastrar Genero");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCadastrarAluno(ActionEvent action) throws IOException{
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroAluno.fxml"));
       
        try {
            Scene s = new Scene((Parent) fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastrar Aluno");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCadastrarProfessor(ActionEvent action) throws IOException{
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroProfessor.fxml"));
       
        try {
            Scene s = new Scene((Parent) fx.load());
            Stage st = new Stage();
            st.setTitle("Cadastrar Professor");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void btnRealizarEmprestimo(ActionEvent action) throws IOException{
        FXMLLoader fx = new FXMLLoader(OpcoesController.class.getResource("/fxml/CadastroEmprestimo.fxml"));
       
        try {
            Scene s = new Scene((Parent) fx.load());
            Stage st = new Stage();
            st.setTitle("Realizar Emprestimo");
            st.setScene(s);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
