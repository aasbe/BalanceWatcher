package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PRINCIPALController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button inicio;
    @FXML
    private Button gastos;
    @FXML
    private Button addGas;
    @FXML
    private Button addCat;
    @FXML
    private Button perfil; // Botón para acceder al perfil
    @FXML
    private Button volver;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void addGasto(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Gasto.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Añadir gasto");
        stage.show();
    }

    @FXML
    public void irPerfil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Perfil.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("PRINCIPAL - Perfil");
        stage.show();
    }
    

    @FXML
    public void volver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Principal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BalanceWatcher");
        stage.show();
    }

    @FXML
    private void irAddCategoria(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Añadir categoría");
        stage.show();
    }

}
