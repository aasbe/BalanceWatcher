/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author caral
 */


public class PRINCIPALController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    private Parent root;    
    
    @FXML
    private Button add;
    private Button atras; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    public void add(ActionEvent event) throws IOException {
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