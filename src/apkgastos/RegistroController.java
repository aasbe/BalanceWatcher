/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apkgastos;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author MVEGICAS
 */
public class RegistroController implements Initializable {

  
    @FXML
    private TextField cvc;
    @FXML
    private TextField nombre;
    @FXML
    private TextField telefono;
    @FXML
    private TextField apellido;
    @FXML
    private TextField tarjeta;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contraseña;
    @FXML
    private PasswordField repetirContraseña;
    @FXML
    private Button botonUnirse;
    @FXML
    private Button botonVolver;
    @FXML
    private Button botonAvatar;
    private Image imagen = new Image(getClass().getResourceAsStream("..\\avatares\\default.png"));
    private String Usuario ;
    private boolean seHaPulsadoBotonUnirse ;
    @FXML
    private TextField mostrarContraseña;
    @FXML
    private TextField mostrarRepetirContraseña;
    @FXML
    private ImageView ojoContr;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
     botonVolver.setCursor(Cursor.HAND);
     botonUnirse.setCursor(Cursor.HAND);
     seHaPulsadoBotonUnirse = false; 
    }    
    
    
    
    @FXML
    public void volver(ActionEvent event) throws IOException{
        ((Button)event.getSource()).getScene().getWindow().hide(); 
    }
    
    @FXML
    public void unirse(ActionEvent event) throws IOException{
        
        
        String Tarjeta = null;
        int CVC=0;
        
        
        if(nombre.getText().isEmpty()||apellido.getText().isEmpty()||usuario.getText().isEmpty()||
                contraseña.getText().isEmpty()||
                repetirContraseña.getText().isEmpty()||telefono.getText().isEmpty())
            
        {    
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR EN LOS CAMPOS OBLIGATORIOS");
            alert.setContentText("Rellene todos los campos indicados como obligatorios");
            
            alert.showAndWait();
        
        }else{
            
            



            String Telefono= telefono.getText();
            boolean isNumericN = (Telefono != null && Telefono.matches("[0-9]+") ) ;

            String Nombre= nombre.getText();
            String Apellido= apellido.getText();
            Usuario= usuario.getText();
            String Contraseña= contraseña.getText();
            String RepetirContraseña = repetirContraseña.getText();
            
            
            if(contraseña.getText().length()<7 || !( contraseña.getText()!= null && contraseña.getText().matches("[0-9a-zA-Z]+") )){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN CONTRASEÑA");
                    alert.setContentText("La contraseña debe contener mas de 6 numeros/letras");
                    alert.showAndWait();
                    contraseña.clear();
                    contraseña.setPromptText("7 o más carácteres");
                    
            
            }else if(!Contraseña.equals(RepetirContraseña)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN LA CONTRASEÑA");
                alert.setContentText("No coinciden los campos de contraseña");
                alert.showAndWait();
                repetirContraseña.clear();
                repetirContraseña.setPromptText("Coincida con contraseña");

            }else if(Telefono.length()!=9 || !isNumericN){

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN EL Nº DE TELÉFONO");
                alert.setContentText("El campo Teléfono debe contener 9 dígitos");
                alert.showAndWait();
                telefono.clear();
                telefono.setPromptText("9 dígitos");



            }else if((!tarjeta.getText().equals(""))&&(!cvc.getText().equals(""))){
                if(tarjeta.getText().length()==19 && ( tarjeta.getText()!= null && tarjeta.getText().matches("[0-9 ]+") )
                        && cvc.getText().length()==3 && ( cvc.getText()!= null && cvc.getText().matches("[0-9]+") ) ) {
                
                    Tarjeta = tarjeta.getText();
                    CVC = Integer.parseInt(cvc.getText());
                   try{
                         
                        ((Button)event.getSource()).getScene().getWindow().hide();
                    }catch(Exception e){ 
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("ERROR EN USUARIO");
                        alert.setContentText("Este nombre de usuario ya está en uso");
                        alert.showAndWait();
                        usuario.clear();
                        usuario.setPromptText("Escoja otro usuario");
                    };   



                    
                    
                    
                    
                }else if(tarjeta.getText().length()!=19 || ! (tarjeta.getText()!= null && tarjeta.getText().matches("[0-9 ]+")) ){
                    
                    
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN EL Nº DE LA TARJETA");
                    alert.setContentText("Formato correcto: XXXX XXXX XXXX XXXX (siendo X un numero)");
                    alert.showAndWait();
                    tarjeta.clear();
                    tarjeta.setPromptText("XXXX XXXX XXXX XXXX");
                    
               }else if(cvc.getText().length()!=3 || !( cvc.getText()!= null && cvc.getText().matches("[0-9]+") )){ 
                   
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN EL CVC");
                    alert.setContentText("Formato correcto: XXX (siendo X un numero)");
                    alert.showAndWait();
                    
                    cvc.clear();
                    cvc.setPromptText("XXX");
               }
            }else if((!tarjeta.getText().isEmpty())||(!cvc.getText().isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR AL AÑADIR TARJETA");
                alert.setContentText("Añadir Nº tarjeta y el CVC a la vez");
                alert.showAndWait();
                
            
            }else{

            
                
               try{
                          
                    ((Button)event.getSource()).getScene().getWindow().hide();
                    }catch(Exception e){ 
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("ERROR EN USUARIO");
                        alert.setContentText("Este nombre de usuario ya está en uso");
                        alert.showAndWait();
                        usuario.clear();
                        usuario.setPromptText("Escoja otro usuario");
                    };    
                
            }
        }        
    }

    @FXML
    private void avatar(ActionEvent event) throws IOException {
         FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/tenisclubtest/Imagen.fxml"));
        Parent root = miCargador.load();
        ImagenController controladorImagen = miCargador.getController();

        // acceso al controlador de datos persona
        controladorImagen.introducirImagen(imagen);
        Scene scene = new Scene(root);
        Stage stage = new Stage();    //(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Selecciona tu avatar");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        if (controladorImagen.isOKPressed()) {
            imagen= controladorImagen.getImagen();
        }
        

    }
    
    public String getUsuario(){
        return Usuario;
    }
    
    public boolean seHaPulsado(){
        return seHaPulsadoBotonUnirse;
    }
    
    

    @FXML
    private void unirseEnter(KeyEvent event) {
         String Tarjeta = null;
        int CVC=0;
        
        if(event.getCode() == KeyCode.ENTER){
            if(nombre.getText().isEmpty()||apellido.getText().isEmpty()||usuario.getText().isEmpty()||
                contraseña.getText().isEmpty()||
                repetirContraseña.getText().isEmpty()||telefono.getText().isEmpty())
            
        {    
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR EN LOS CAMPOS OBLIGATORIOS");
            alert.setContentText("Rellene todos los campos indicados como obligatorios");
            
            alert.showAndWait();
        
        }else{
            
            



            String Telefono= telefono.getText();
            boolean isNumericN = (Telefono != null && Telefono.matches("[0-9]+") ) ;

            String Nombre= nombre.getText();
            String Apellido= apellido.getText();
            Usuario= usuario.getText();
            String Contraseña= contraseña.getText();
            String RepetirContraseña = repetirContraseña.getText();
            
            
            if(contraseña.getText().length()<7 || !( contraseña.getText()!= null && contraseña.getText().matches("[0-9a-zA-Z]+") )){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN CONTRASEÑA");
                    alert.setContentText("La contraseña debe contener mas de 6 numeros/letras");
                    alert.showAndWait();
                    contraseña.clear();
                    contraseña.setPromptText("7 o más carácteres");
                    
            
            }else if(!Contraseña.equals(RepetirContraseña)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN LA CONTRASEÑA");
                alert.setContentText("No coinciden los campos de contraseña");
                alert.showAndWait();
                repetirContraseña.clear();
                repetirContraseña.setPromptText("Coincida con contraseña");

            }else if(Telefono.length()!=9 || !isNumericN){

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR EN EL Nº DE TELÉFONO");
                alert.setContentText("El campo Teléfono debe contener 9 dígitos");
                alert.showAndWait();
                telefono.clear();
                telefono.setPromptText("9 dígitos");



            }else if((!tarjeta.getText().equals(""))&&(!cvc.getText().equals(""))){
                if(tarjeta.getText().length()==19 && ( tarjeta.getText()!= null && tarjeta.getText().matches("[0-9 ]+") )
                        && cvc.getText().length()==3 && ( cvc.getText()!= null && cvc.getText().matches("[0-9]+") ) ) {
                
                    Tarjeta = tarjeta.getText();
                    CVC = Integer.parseInt(cvc.getText());
                    try{
                          
                    ((Button)event.getSource()).getScene().getWindow().hide();
                    }catch(Exception e){ 
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("ERROR EN USUARIO");
                        alert.setContentText("Este nombre de usuario ya está en uso");
                        alert.showAndWait();
                        usuario.clear();
                        usuario.setPromptText("Escoja otro usuario");
                    };


                    
                    
                    
                    
                }else if(tarjeta.getText().length()!=19 || ! (tarjeta.getText()!= null && tarjeta.getText().matches("[0-9 ]+")) ){
                    
                    
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN EL Nº DE LA TARJETA");
                    alert.setContentText("Formato correcto: XXXX XXXX XXXX XXXX (siendo X un numero)");
                    alert.showAndWait();
                    tarjeta.clear();
                    tarjeta.setPromptText("XXXX XXXX XXXX XXXX");
                    
               }else if(cvc.getText().length()!=3 || !( cvc.getText()!= null && cvc.getText().matches("[0-9]+") )){ 
                   
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN EL CVC");
                    alert.setContentText("Formato correcto: XXX (siendo X un numero)");
                    alert.showAndWait();
                    
                    cvc.clear();
                    cvc.setPromptText("XXX");
               }
            }else if((!tarjeta.getText().isEmpty())||(!cvc.getText().isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR AL AÑADIR TARJETA");
                alert.setContentText("Añadir Nº tarjeta y el CVC a la vez");
                alert.showAndWait();
                
            
            }else{

            
                
                try{
                     
                    ((Button)event.getSource()).getScene().getWindow().hide();
                }catch(Exception e){ 
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("ERROR EN USUARIO");
                    alert.setContentText("Este nombre de usuario ya está en uso");
                    alert.showAndWait();
                    usuario.clear();
                    usuario.setPromptText("Escoja otro usuario");
                };
                
            }
        } 
        }
    }

    @FXML
    private void ocultarContraseña(MouseEvent event) {
        mostrarContraseña.clear();
        mostrarContraseña.setVisible(false);
        contraseña.setVisible(true);
    }

    @FXML
    private void mostrarContraseña(MouseEvent event) {
        contraseña.setVisible(false);
        mostrarContraseña.setVisible(true);
        mostrarContraseña.setText(contraseña.getText());
    }

    @FXML
    private void ocultarRepContra(MouseEvent event) {
        mostrarRepetirContraseña.clear();
        mostrarRepetirContraseña.setVisible(false);
        repetirContraseña.setVisible(true);
    }

    @FXML
    private void mostrarRepContra(MouseEvent event) {
        repetirContraseña.setVisible(false);
        mostrarRepetirContraseña.setVisible(true);
        mostrarRepetirContraseña.setText(contraseña.getText());
    }
    
    
}
