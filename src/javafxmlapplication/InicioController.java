/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class InicioController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button button3; 
    @FXML
    private Button miembro;
    @FXML
    private Button registrar;
    @FXML
    private Button salirfxID;
    
    @FXML
    public void irAutenticacion(ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("Autenticacion.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Autenticación");
        stage.show();
    } 
    
    @FXML
    public void irRegistro(ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("REGISTRO.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Registro");
        stage.show();
    } 
 @FXML
    private void bSalir(ActionEvent event) {
        
        /*confirmación de salir*/
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("SALIENDO DE LA APLICACIÓN");
        alert.setContentText("¿Seguro que desea salir?");
        if (alert.showAndWait().get() == ButtonType.OK) {
        Stage myStage= (Stage) this.salirfxID.getScene().getWindow();
        myStage.close();}
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}
