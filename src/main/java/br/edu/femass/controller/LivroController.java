package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoCopia;
import br.edu.femass.dao.DaoGenero;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.entities.Copia;
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

public class LivroController implements Initializable{
    private DaoLivro dao = new DaoLivro(Livro.class);
    private DaoCopia daoCopia = new DaoCopia(Copia.class);
    private DaoGenero daoGenero = new DaoGenero(Genero.class);

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtAno;

    @FXML
    private TextField TxtEdicao;

    @FXML
    private TextField TxtCopias;

    @FXML
    private ListView<Livro> listaLivro;

    @FXML
    private ComboBox<Genero> ComboGenero;

    @FXML
    private ListView<Copia> listaCopia;

    @FXML
    private void btnSalvar(ActionEvent event) {
        Alert alerta = new Alert(AlertType.ERROR);

        try {
            if (TxtNome.getText().length() == 0 || 
                TxtAno.getText().length() == 0 ||
                TxtEdicao.getText().length() == 0 ||
                TxtCopias.getText().length() == 0 ||
                ComboGenero.getValue() == null) {
                throw new IllegalArgumentException("Todos os campos são obrigatórios!");
            } else {
                Integer ano = Integer.parseInt(TxtAno.getText());
                Livro livro = new Livro(TxtNome.getText(), ano, TxtEdicao.getText(),
                ComboGenero.getSelectionModel().getSelectedItem());

                for(int i = 0; i < Integer.parseInt(TxtCopias.getText()); i++) {
                    Copia copia = new Copia(TxtNome.getText());
                    daoCopia.create(copia);

                    livro.addCopia(copia);
                }

                dao.create(livro);
            }
            
        } catch (Exception e) {
            alerta.setTitle(e.getMessage());
            alerta.show();
        }

        TxtNome.setText("");
        TxtAno.setText("");
        TxtEdicao.setText("");
        ComboGenero.getSelectionModel().select(null);
        TxtCopias.setText("");

        preencherListaLivro();
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
        Livro livro = listaLivro.getSelectionModel().getSelectedItem();

        if(livro == null) return;
        try  {
            dao.delete(livro.getId());
            TxtNome.setText("");
            TxtAno.setText("");
            TxtEdicao.setText("");
            ComboGenero.getSelectionModel().select(null);
            TxtCopias.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        preencherListaLivro();
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        TxtNome.setText("");
        TxtAno.setText("");
        TxtEdicao.setText("");
        ComboGenero.getSelectionModel().select(null);
        TxtCopias.setText("");
    }

    @FXML
    private void listaAutor_mouseClicked(MouseEvent event){
       exibirDados();
       preencherListaCopia();
    }

    @FXML
    private void listaAutor_keyPressed(KeyEvent event){
        exibirDados();
        preencherListaCopia();
    }

    private void exibirDados() {
        Livro livro = listaLivro.getSelectionModel().getSelectedItem();
        if(livro == null) return;
        
        TxtNome.setText(livro.getNome());
        TxtAno.setText(Integer.toString(livro.getAno()));
        TxtEdicao.setText(livro.getEdicao());
        ComboGenero.getSelectionModel().select(livro.getGenero());
    }

    public void preencherListaCopia() {
        Livro livro = listaLivro.getSelectionModel().getSelectedItem();
        if(livro == null) return;
        
        List<Copia> copias = dao.buscarCopias(livro);
        ObservableList<Copia> data = FXCollections.observableArrayList(copias);
        listaCopia.setItems(data);
        // List<Livro> livros = dao.buscar();
        // ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        // listaLivro.setItems(data);
    }

    public void exibirGeneros() {
        try {
            ObservableList<Genero> data = FXCollections.observableArrayList(daoGenero.buscar());
            ComboGenero.setItems(data);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void preencherListaLivro() {
        List<Livro> livros = dao.buscar();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        listaLivro.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirGeneros();
        preencherListaLivro();
    }
}
