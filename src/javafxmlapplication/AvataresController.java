/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.Image;


/**
 
 */

public class AvataresController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 

    @FXML
    private Button seleccionar;
    @FXML
    private Button volver;
    @FXML
    private ImageView persona;
    @FXML
    private ImageView abeja;
    @FXML
    private ImageView delfin;
    @FXML
    private ImageView vaca;
    @FXML
    private ImageView cerdo;
    @FXML
    private ImageView ardilla;
    @FXML
    private ImageView zorro;
    @FXML
    private ImageView panda;
    @FXML
    private ImageView pato;
    @FXML
    private ImageView elegido;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    /*AÑADIR IMAGEN DE OTRO ARCHIVO*/
    
   

    /**
     * Initializes the controller class.
     */
     
    @FXML
    private void seleccionar(ActionEvent event) {
    }
    
    @FXML
    public void volver(javafx.event.ActionEvent event) throws IOException {
        Stage myStage= (Stage) this.volver.getScene().getWindow();
        myStage.close();
    } 
    

    
    public javafx.scene.image.Image getImagen(){
        return elegido.getImage();
    }
    
    public void introducirImagen(javafx.scene.image.Image i){
        elegido.setImage(i);
        
    }


    @FXML
    private void persona(MouseEvent event) {
        
        
        elegido.setImage(persona.getImage());
    }

    @FXML
    private void abeja (MouseEvent event) {
        
        elegido.setImage(abeja.getImage());
    }

    @FXML
    private void delfin(MouseEvent event) {
        
        elegido.setImage(delfin.getImage());
    }

    @FXML
    private void vaca(MouseEvent event) {
        
        elegido.setImage(vaca.getImage());
    }

    @FXML
    private void cerdo(MouseEvent event) {
        
        elegido.setImage(cerdo.getImage());
    }

    @FXML
    private void ardilla(MouseEvent event) {
        
        elegido.setImage(ardilla.getImage());
    }

    @FXML
    private void zorro (MouseEvent event) {
        
        elegido.setImage(zorro.getImage());
    }

    @FXML
    private void panda(MouseEvent event) {
        
        elegido.setImage(panda.getImage());
    }

    @FXML
    private void pato(MouseEvent event) {
        
        elegido.setImage(pato.getImage());
    }
    
}
