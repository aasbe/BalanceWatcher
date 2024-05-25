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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Acount;
import model.AcountDAOException;
import model.Category;

public class VerCategoriasController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    

    @FXML
    private Button inicio;
    @FXML
    private Button gastos;
    @FXML
    private Button eliminar;
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
    private Button cerrarS;
    @FXML
    private ListView listaCategorias;
    @FXML
    public boolean actualizar = false;
    
   /* private ObservableList<Category> todasCategorias;*/
    private List <Category> todasCat;
    
    private ObservableList<Category> catSeleccionadas;
    private Category catSeleccionada;
    private String usuario;


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
            todasCat = (Acount.getInstance().getUserCategories());
        } catch (AcountDAOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nick.setText(usuario);
        listaCategorias.getItems().addAll(todasCat);
        
        listaCategorias.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                return new ListCell<Category>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        } else {
                            setText(null);
                        }
                    }
                        };
            }
        });
        catSeleccionadas = listaCategorias.getSelectionModel().getSelectedItems();
        
    
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
    
    @FXML
    private void eliminarCategoria (ActionEvent event)  throws IOException {
        
    catSeleccionada = catSeleccionadas.get(0);
    boolean eliminar = true;
    try {
                                                                     
            if (eliminar) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar categoría");
        alert.setHeaderText("¿Quiere eliminar la categoría seleccionada? Se eliminarán todos los gastos asociados.");
        if (alert.showAndWait().get() == ButtonType.OK) {
        eliminar = Acount.getInstance().removeCategory(catSeleccionada);
        actualizarCategorias();
        }
    }
            
    }
    catch (AcountDAOException error){
    
    Alert errorUs = new Alert(Alert.AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("No se pudo eliminar la categoría.");
                errorUs.showAndWait();
                }
    
}
    
    @FXML
    public void actualizarCategorias() throws IOException{
             try {
            todasCat = (Acount.getInstance().getUserCategories());
        } catch (AcountDAOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaCategorias.getItems().clear();
        listaCategorias.getItems().addAll(todasCat);
        
        listaCategorias.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                return new ListCell<Category>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        } else {
                            setText(null);
                        }
                    }
                        };
            }
        });
        catSeleccionadas = listaCategorias.getSelectionModel().getSelectedItems(); 
    }
    
    @FXML
    private void irAddCategoria(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); /*Aquí, para pop up new Stage*/
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Añadir categoría");
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
