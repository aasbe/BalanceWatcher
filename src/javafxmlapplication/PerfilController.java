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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private Button cerrarS;
    @FXML
    private ImageView avatar;        
    
    String nnombre;
    String ncorreo;
    String usuario;
    String ncontraseña;
    String napellido;
    LocalDate fecha;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Image imagenPerfil;
    
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
            imagenPerfil = Acount.getInstance().getLoggedUser().getImage();
            
            
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
        avatar.setImage(imagenPerfil);
    }
    
    @FXML
    public void ActualizarAvatar (javafx.event.ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActualizarAvatar.fxml"));
        root =  loader.load();
        stage = new Stage();/*(Stage) ((Node)event.getSource()).getScene().getWindow(); En pop-up*/
        /*PARA PASAR OBJETOS ENTRE CONTROLLERS*/
        ActualizarAvatarController actualizarAvatarcontroller = loader.getController();
        actualizarAvatarcontroller.introducirImagen(imagenPerfil);
        
        stage.setResizable(false);
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Actualizar foto de perfil");
        stage.showAndWait(); /*CLAVE EL SHOWANDWAIT*/
        if (actualizarAvatarcontroller.isISelected()) {
            imagenPerfil= actualizarAvatarcontroller.getImage();
            avatar.setImage(imagenPerfil);}
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
         Acount.getInstance().getLoggedUser().setImage(imagenPerfil);
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
    
        @FXML
    public void cerrarSesion(ActionEvent event) throws IOException {
        
        try {
         /*confirmación de cerrar sesión*/
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar sesión");
        alert.setHeaderText("CERRANDO SESIÓN");
        alert.setContentText("¿Seguro que desea cerrar la sesión?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            boolean cerrar =  Acount.getInstance().logOutUser();
        root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
        }
        
        catch (AcountDAOException error) {    
         Alert errorUs = new Alert(Alert.AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("Error al cerrar sesión.");
                errorUs.showAndWait();  
        }
    }
}
