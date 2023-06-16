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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML
    private TextField TxtUsuario;

    @FXML
    private PasswordField TxtSenha;

    @FXML
    private void btnLogar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try {
            if (TxtUsuario.getText().length() == 0 ||
            TxtSenha.getText().length() == 0) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                if (TxtUsuario.getText().equals("bibliotecario") &&
                TxtSenha.getText().equals("bibliotecario123")) {
                    Parent root = FXMLLoader.load(LoginController.class.getResource("/fxml/Opcoes.fxml"));

                    try {
                        Scene s = new Scene(root);
                        Stage st = new Stage();
                        st.setTitle("Cadastro Paciente");
                        st.setScene(s);
                        st.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        throw new IllegalArgumentException("Usuário e senhas incorretos!");

                    } catch (Exception e) {
                        alerta.setTitle(e.getMessage());
                        alerta.show();
                    }
                }
            }
        } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }
    }

    @FXML
    private void btnSenha(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try {
            
        throw new IllegalArgumentException("Vai ter que lembrar, não tem como recuperar :(");
        } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }
    }

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    
}
