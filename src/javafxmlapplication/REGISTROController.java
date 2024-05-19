/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

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
    
    Image image = new Image ("logoproject.jpg"); /*HACER AÑADIR IMAGEN*/
    
    private boolean newuser = false;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void newuser(javafx.event.ActionEvent event) throws IOException {
        
        String Nombre = nombre.getText();
        String Correo = correo.getText();
        String Usuario = usuario.getText(); /*ÚNICO: Avisar al usuario?*/
        String Contraseña = contraseña.getText();
        
        try{
        /*CONDICIONES, 3 mensajes de error*/
        if (Nombre.isEmpty()||Correo.isEmpty()||Usuario.isEmpty()||Contraseña.isEmpty()||
                Usuario.contains(" ")||Contraseña.length()<7){
            
            if (Nombre.isEmpty()||Correo.isEmpty()||Usuario.isEmpty()||Contraseña.isEmpty()) {
            Alert error = new Alert (AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("No ha rellenado todos los campos obligatorios * ");
            error.showAndWait();
            }
        
            if (Usuario.contains(" ")){
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("El usuario no ha de tener espacios");
                error.showAndWait();
                usuario.clear();
            }
            
            if(Contraseña.length()<7){
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("La contraseña ha de tener mínimo 7 carácteres");
                error.showAndWait();
                contraseña.clear();
            }
        }
         
        /*REGISTRO*/
        else {
            LocalDate fecha = LocalDate.now();
            
        newuser = Acount.getInstance().registerUser(Nombre,
            "APELLIDO",Correo,Usuario,Contraseña, image,fecha);
    System.out.println(Usuario+" registrado!"); 
    
    /* -> METER VENTANA y autenticar */
    
    if (newuser == true){ 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuario nuevo registrado");
        alert.setHeaderText("Se ha registrado correctamente el usuario "+ Usuario +". Pulse 'OK' para ir a la ventana de Autenticación.");
       /* alert.setContentText("Se ha registrado el nuevo usuario "+ Usuario +". Pulse aceptar para ir a la ventana de Autenticación");*/
        if (alert.showAndWait().get() == ButtonType.OK) {
        irAutenticacion(event);}
    }
        }     
        }
      catch  (AcountDAOException error) {
          Alert errorUs = new Alert(AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("Nombre de usuario ya registrado");
                errorUs.showAndWait();
                usuario.clear();
          System.out.println("Usuario ya registrado");}   
    }

    
    @FXML
    public void SeleccionarAvatar (javafx.event.ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("Avatares.fxml"));
        stage = new Stage();/*(Stage) ((Node)event.getSource()).getScene().getWindow(); En pop-up*/
        stage.setResizable(false);
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Foto de perfil");
        stage.show();
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
    
    @FXML
    public void irAutenticacion(javafx.event.ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("Autenticacion.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Autenticación");
        stage.show();
    } 
    
}