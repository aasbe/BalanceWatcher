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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author caral
 */
public class AutenticacionController implements Initializable {

    @FXML
    private Button volver;
    @FXML
    private Button accederB;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    
    private boolean accedido = false;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    public void acceder(javafx.event.ActionEvent event) throws IOException, AcountDAOException {
        /*LOGIN */
        accedido = Acount.getInstance().logInUserByCredentials(usuario.getText(),password.getText());
        
        if (accedido)   {    
        root =  FXMLLoader.load(getClass().getResource("PRINCIPAL.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Principal");
        stage.show();
        }
        
        else{
          Alert errorAu = new Alert(AlertType.ERROR);
                errorAu.setTitle("Error");
                errorAu.setHeaderText("Nombre de usuario/contraseña no registrados");
                errorAu.showAndWait();
                usuario.clear();
                password.clear();   
        }
    }
    @FXML
    public void volver(javafx.event.ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("BalanceWatcher");
        stage.show();
    } 
    
    
}