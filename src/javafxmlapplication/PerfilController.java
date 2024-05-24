/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

/**
 * FXML Controller class
 *
 * @author caral
 */
public class PerfilController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private Label nombre2;
    @FXML
    private TextField correo;
    @FXML
    private Label nick;
    @FXML
    private PasswordField contraseña;
    @FXML
    private TextField apellido;
    @FXML
    private Label fechat;
    
    String nnombre;
    String ncorreo;
    String usuario;
    String ncontraseña;
    String napellido;
    LocalDate fecha;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nnombre = Acount.getInstance().getLoggedUser().getName();
            napellido = Acount.getInstance().getLoggedUser().getSurname();
            usuario = Acount.getInstance().getLoggedUser().getNickName();
            ncontraseña = Acount.getInstance().getLoggedUser().getPassword();
            ncorreo = Acount.getInstance().getLoggedUser().getEmail();
            fecha = Acount.getInstance().getLoggedUser().getRegisterDate();
            
        } catch (AcountDAOException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        nombre.setText(nnombre);
        apellido.setText(napellido);
        nick.setText(usuario);
        correo.setText(ncorreo);
        contraseña.setText(ncontraseña);
        fechat.setText(fecha.toString());
    }
    
    @FXML
    public void guardarDatos (ActionEvent event)throws IOException{
        nnombre = nombre.getText();
        napellido = apellido.getText();
        ncontraseña = contraseña.getText();
        ncorreo = correo.getText();
        
        try {
            if ( nnombre.isEmpty()||napellido.isEmpty()||ncontraseña.isEmpty()||
                 ncorreo.isEmpty() ||ncontraseña.length()<7)
                {
            
            if (nnombre.isEmpty()||napellido.isEmpty()||ncontraseña.isEmpty()||
                 ncorreo.isEmpty()) {
            Alert error = new Alert (Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("No ha rellenado todos los campos obligatorios * ");
            error.showAndWait();
            }
            
            if(ncontraseña.length()<7){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("La contraseña ha de tener mínimo 7 carácteres");
                error.showAndWait();
                contraseña.clear();
            }   
        }
            
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cambios");
        alert.setHeaderText("Quiere confirmar los cambios para el usuario "+ usuario +"?");
        if (alert.showAndWait().get() == ButtonType.OK) {
         Acount.getInstance().getLoggedUser().setName(nnombre);
         Acount.getInstance().getLoggedUser().setSurname(napellido);
         Acount.getInstance().getLoggedUser().setPassword(ncontraseña);
         Acount.getInstance().getLoggedUser().setEmail(ncorreo);  
        volver(event);
        }
            }
            
        } catch (AcountDAOException ex) {    
            
        }
    }  
    
    @FXML
    public void irPrincipal(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PRINCIPAL.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Principal");
        stage.show();
    }
 
    @FXML
    public void irVerGastos(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("VerGastos.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Gastos");
        stage.show();
    }
    
    @FXML
    public void irVerCategorias(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("VerCategorias.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("PRINCIPAL - Categorías");
        stage.show();
    }    
         
     

        @FXML
    public void volver(javafx.event.ActionEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("PRINCIPAL.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("PRINCIPAL");
        stage.show();
    } 
}
