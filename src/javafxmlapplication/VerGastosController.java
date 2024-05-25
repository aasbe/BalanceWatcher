package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;

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
    private Button cerrarS;
    @FXML
    private TableView visor;
    @FXML
    private TableColumn <Charge, String> gasto; // <From Type, Type gonnabe>
    @FXML
    private TableColumn <Charge, Category> categoria; 
    @FXML
    private TableColumn <Charge, Double> coste;
    @FXML
    private TableColumn <Charge, LocalDate> fecha;
    
    private ObservableList<Charge> todosGastos = FXCollections.observableArrayList();
    private ObservableList<Charge> gastoSelect;
    private List <Charge> todosGas;
    private Charge gastoActual;
    
    
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
            todosGas = (Acount.getInstance().getUserCharges());
        } catch (AcountDAOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nick.setText(usuario);
        
        /*CARGAR TABLA*/
        
        todosGastos.addAll(todosGas);
         /*Pillas los nombres de los atributos (entre paréntesis) de lo que quieres que se vea*/
        gasto.setCellValueFactory(new PropertyValueFactory <Charge, String>("name"));
        categoria.setCellValueFactory(new PropertyValueFactory <Charge, Category>("category"));     
        /*Para que se vea legible el nombre de las categorías*/
        categoria.setCellFactory(new Callback<TableColumn<Charge, Category>, TableCell<Charge,Category>>() {
            @Override
            public TableCell<Charge, Category> call(TableColumn<Charge, Category> param) {
                return new TableCell<Charge, Category>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        } else {
                            setText(null);
                        };
                    }
                        };
            }
        });
        coste.setCellValueFactory(new PropertyValueFactory<Charge, Double>("cost"));
        /*Añadir € */
        coste.setCellFactory(new Callback<TableColumn<Charge, Double>, TableCell<Charge,Double>>() {
            @Override
            public TableCell<Charge, Double> call(TableColumn<Charge, Double> param) {
                return new TableCell<Charge, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item + " €");
                        } else {
                            setText(null);
                        };
                    }
                        };
            }
        });
        fecha.setCellValueFactory(new PropertyValueFactory<Charge, LocalDate>("date"));
        
        visor.setItems(todosGastos);
        gastoSelect = visor.getSelectionModel().getSelectedItems();
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
    public void actualizarGastos ()  throws IOException {
        try {
            visor.getItems().clear();
            todosGas = (Acount.getInstance().getUserCharges());
            
            todosGastos.addAll(todosGas);
            
        
         /*Pillas los nombres de los atributos (entre paréntesis) de lo que quieres que se vea*/
        gasto.getColumns().clear();
        gasto.setCellValueFactory(new PropertyValueFactory <Charge, String>("name"));
        
        categoria.getColumns().clear();
        categoria.setCellValueFactory(new PropertyValueFactory <Charge, Category>("category"));     
        /*Para que se vea legible el nombre de las categorías*/
        categoria.setCellFactory(new Callback<TableColumn<Charge, Category>, TableCell<Charge,Category>>() {
            @Override
            public TableCell<Charge, Category> call(TableColumn<Charge, Category> param) {
                return new TableCell<Charge, Category>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        } else {
                            setText(null);
                        };
                    }
                        };
            }
        });
        coste.getColumns().clear();
        coste.setCellValueFactory(new PropertyValueFactory<Charge, Double>("cost"));
        /*Añadir € */
        coste.setCellFactory(new Callback<TableColumn<Charge, Double>, TableCell<Charge,Double>>() {
            @Override
            public TableCell<Charge, Double> call(TableColumn<Charge, Double> param) {
                return new TableCell<Charge, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item + " €");
                        } else {
                            setText(null);
                        };
                    }
                        };
            }
        });
        fecha.getColumns().clear();
        fecha.setCellValueFactory(new PropertyValueFactory<Charge, LocalDate>("date"));
        
        
        visor.setItems(todosGastos);
        gastoSelect = visor.getSelectionModel().getSelectedItems();
            
        }
        
         catch (AcountDAOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);}
        catch (IOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    
    @FXML
    private void eliminarGasto (ActionEvent event)  throws IOException {
        
    gastoActual = gastoSelect.get(0);
    boolean eliminar = true;
    try {
                                                                     
            if (eliminar) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar gasto");
        alert.setHeaderText("¿Quiere eliminar el gasto seleccionado?");
        if (alert.showAndWait().get() == ButtonType.OK) {
        eliminar = Acount.getInstance().removeCharge(gastoActual);
       actualizarGastos();
        }
            }
    }
    
        catch (AcountDAOException error){
    
    Alert errorUs = new Alert(Alert.AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("No se pudo eliminar el gasto");
                errorUs.showAndWait();
                }
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
