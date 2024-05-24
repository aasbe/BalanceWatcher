package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;
import model.Category;

public class VerGastosController implements Initializable {

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
    private Button verCat;
    @FXML
    private Label nick;
    @FXML
    private Button perfil; // Botón para acceder al perfil
    @FXML
    private Button volver;
    @FXML
    private ListView listaCategorias;
    
    private ObservableList<Category> todasCategorias;
    private List <Category> todasCat;
    
    String usuario;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
            try {
        todasCat= (Acount.getInstance().getUserCategories());
        todasCategorias.setAll(todasCat);
        }
        catch (AcountDAOException error) {
             Alert errorUs = new Alert(Alert.AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("No hay categorías de gastos registradas");
                errorUs.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(GastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
            listaCategorias.setItems(todasCategorias);
*/
        try {
            usuario = Acount.getInstance().getLoggedUser().getNickName();
        } catch (AcountDAOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nick.setText(usuario);
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
    public void irVerCategorias(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("VerCategorias.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Categorías");
        stage.show();
    }
    
    @FXML
    public void irPerfil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Perfil.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Perfil");
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

        public void addGasto(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Gasto.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Añadir gasto");
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
