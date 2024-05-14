/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author MVEGICAS
 */
public class MiembroController implements Initializable {

    @FXML
    private CheckBox mostrarfxID;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contraseña;
    @FXML
    private Label muestrafxID;
    @FXML
    private Button volver;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void bMostrar(ActionEvent event) {
        if(mostrarfxID.isSelected()){
           muestrafxID.setVisible(true);
           muestrafxID.textProperty().bind(contraseña.textProperty());
        }else if(!mostrarfxID.isSelected()){
            muestrafxID.setVisible(false);
        }
    }
    
    /* COMENTO DESDE AQUÍ PARA COMPILAR
    @FXML
    public void acceder(ActionEvent event) throws IOException{
        
        boolean existe = club.existsLogin(usuario.getText());
        
        if(!existe){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN USUARIO");
                alert.setContentText("El usuario no está registrado");

                alert.showAndWait();
            
        }else{

            Member member = club.getMemberByCredentials(usuario.getText(),contraseña.getText());
            

            if(member==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN CONTRASEÑA");
                alert.setContentText("La contraseña no es correcta");

                alert.showAndWait();
            }else{
            
                
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/tenisclubtest/Pista.fxml"));
            
            
            Parent root = miCargador.load();
            PistaController controlador = miCargador.getController();
            // acceso al controlador de datos persona
           controlador.introducirMiembro(member);
            Scene scene = new Scene(root);
            Stage stage =  (Stage) ((Node)event.getSource()).getScene().getWindow(); 
            stage.setScene(scene);
            stage.setTitle("Pistas");
            
            stage.show();

            }
        }
    }
    
    @FXML
    public void vueltaInicio(ActionEvent event) throws IOException{
        FXMLLoader volverInicio = new FXMLLoader(getClass().getResource("/tenisclubtest/Inicio.fxml"));
        Parent root = volverInicio.load();
        // acceso al controlador de datos persona
        Scene scene = new Scene(root);
        
        TCDataGenerator.stage.setScene(scene);
        TCDataGenerator.stage.setTitle("GreenBall");
        
        TCDataGenerator.stage.show();
    }

    @FXML
    private void entrar(KeyEvent event) throws IOException {
        boolean existe = club.existsLogin(usuario.getText());
        if(event.getCode() == KeyCode.ENTER){
        if(!existe){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN USUARIO");
                alert.setContentText("El usuario no está registrado");

                alert.showAndWait();
            
        }else{

            Member member = club.getMemberByCredentials(usuario.getText(),contraseña.getText());
            
            

            if(member==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN CONTRASEÑA");
                alert.setContentText("La contraseña no es correcta");

                alert.showAndWait();
                
                
            }else{
            
                
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/tenisclubtest/Pista.fxml"));
            
            
            Parent root = miCargador.load();
            PistaController controlador = miCargador.getController();
            // acceso al controlador de datos persona
           controlador.introducirMiembro(member);
            Scene scene = new Scene(root);
            Stage stage =  (Stage) ((Node)event.getSource()).getScene().getWindow(); 
            stage.setScene(scene);
            stage.setTitle("Pistas");
            
            stage.show();

            }
        }

        }
    }
    */
    
}
