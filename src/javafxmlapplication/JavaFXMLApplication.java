 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class JavaFXMLApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Inicio.fxml"));
        Parent root = loader.load();
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena
        Scene scene = new Scene(root,800,400);
        //======================================================================
        
        //Logo del proyecto
        Image icon = new Image ("coins.png");
        stage.getIcons().add(icon);
        //.css
        String css = this.getClass().getResource("estiloBotones.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        stage.setScene(scene);
        stage.setTitle("BalanceWatcher");
      //  stage.setFullScreen(true);
        
        stage.show();
        stage.setOnCloseRequest(event-> { event.consume(); bSalir(stage);});
    }

    private void bSalir(Stage stage) {
        
        /*confirmación de salir*/
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("SALIENDO DE LA APLICACIÓN");
        alert.setContentText("¿Seguro que desea salir?");
        if (alert.showAndWait().get() == ButtonType.OK) {
        stage.close();}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }


    
}