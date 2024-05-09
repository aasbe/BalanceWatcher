/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author caral
 */
public class REGISTROController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField correo;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contraseña;
    @FXML
    private Button siguiente;
    @FXML
    private Button atras;
    @FXML
    private Button selecciona;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void siguiente(ActionEvent event)throws IOException{
        
            String Nombre = nombre.getText();
            String Correo = correo.getText();
            String Usuario = usuario.getText();
            String Contraseña = contraseña.getText();
        
        if(Nombre.isEmpty()||Correo.isEmpty()||Usuario.isEmpty()||Contraseña.isEmpty()){
            
            Alert error = new Alert (AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("No ha rellenado todos los campos obligatorios * ");
            error.showAndWait();
        }
        else{
            
            if(Usuario.contains(" ")){
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("El usuario no ha de tener espacios");
                error.showAndWait();
                usuario.clear();
            }
            else if(Contraseña.length()<7){
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("La contraseña ha de tener mínimo 6 carácteres");
                error.showAndWait();
                contraseña.clear();
            }
           
        }
    }
    
    @FXML
    public void atras (ActionEvent event) throws IOException{
        ((Button)event.getSource()).getScene().getWindow().hide();
    }
    
}
