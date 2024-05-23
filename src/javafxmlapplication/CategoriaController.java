/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;


public class CategoriaController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button volver;
    @FXML
    private Button guardar;
    @FXML
    private TextField categoria;
    @FXML
    private TextField descripcion;
   
    /* Para usar los métodos: Acount.getInstance().*metodoquequieras()*; */
    
    @FXML
    public void addCategoria(ActionEvent event) throws IOException  {
        String Categoria = categoria.getText();
        try{
        
            if (Categoria.isEmpty()) {
            Alert error = new Alert (Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("El campo 'Categoría' no puede estar vacío");
            error.showAndWait();
            }
            else {
    boolean cat = Acount.getInstance().registerCategory(categoria.getText(), descripcion.getText());
    System.out.println(Acount.getInstance().getUserCategories());
    if (cat) {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }
    }
        }
        
        catch (AcountDAOException error) {
        Alert errorUs = new Alert(Alert.AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("Nombre de categoría ya registrada");
                errorUs.showAndWait();
                categoria.clear();
          }     
        }
    
    
    @FXML
    public void cancel(ActionEvent event) throws IOException {

        
        Stage myStage= (Stage) this.volver.getScene().getWindow();
        myStage.close();/*También se puede hide, no sé la diferencia*/
        
    }
}
