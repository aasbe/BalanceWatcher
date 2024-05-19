/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class PRINCIPALController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Pane salir;
    @FXML
    private Button inicio;
    @FXML
    private Button perfil;
    @FXML
    private Button categorias;
    @FXML
    private Button gastos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void botonSalir(MouseEvent event) {
    }

    @FXML
    private void botoninicio(ActionEvent event) {
    }

    @FXML
    private void botonPerfil(ActionEvent event) {
    }

    @FXML
    private void botonCategorias(ActionEvent event) {
    }

    @FXML
    private void botonGastos(ActionEvent event) {
        
    }
    
    @FXML
    public void addGasto(ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("Gasto.fxml")); //Para hacerlo ventana pop-up:
        stage = new Stage(); /*CREAS UNA NUEVA (Stage) ((Node)event.getSource()).getScene().getWindow(); vs Cambias de escena*/
        scene = new Scene(root);
        stage.setScene(scene);
        /*stage.initModality(Modality.APPLICATION_MODAL); Para ventana bloqueante*/
        stage.setTitle("Añadir gasto");
        stage.show();
    }
    
    @FXML
    public void volver(ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BalanceWatcher");
        stage.show();
    } 
    
}
