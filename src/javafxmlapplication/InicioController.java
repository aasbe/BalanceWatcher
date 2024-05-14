package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author MVEGICAS
 */
public class InicioController implements Initializable {    
    
    
    @FXML
    private Button miembro;
    @FXML
    private Button registrar;
    @FXML
    private Button disponibilidad;
    @FXML
    private Button salirfxID;
    @FXML
    private Text texto;
    @FXML
    private String imprimir = "no entra";
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
   
    
    /*Ir a la escena "Miembro"*/
    @FXML
    private void irMiembro(ActionEvent event) throws IOException {
        texto.setText("");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Miembro.fxml"));
        Parent root = loader.load();
        // acceso al controlador de datos persona
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        stage.setScene(scene);
        stage.setTitle("Acceder");
        stage.show();
       
    }
    /*Ir a la escena REGISTRO*/
    @FXML
    private void irRegistrar(ActionEvent event) throws IOException{
        
        texto.setText("");
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("REGISTRO.fxml"));
        
        Parent root = miCargador.load();
        //Lo comento para que compile
        // AQUI (1/2) RegistroController controlador = miCargador.getController();
        // acceso al controlador de datos persona
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registrarse");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
       /*AQUI (2/2) if(controlador.seHaPulsado()){
            texto.setText("Ya has sido registrado, "+controlador.getUsuario()+". Inicia sesión para reservar pista.");
        } */
        //texto.setText(imprimir);
        
    }

    @FXML
    private void bSalir(ActionEvent event) {
        
        Stage myStage= (Stage) this.salirfxID.getScene().getWindow();
        myStage.close();
    }
    
    
    
}