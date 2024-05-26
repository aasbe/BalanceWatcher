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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Acount;
import model.AcountDAOException;
import model.Category;
import model.Charge;

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
    private Button verCat;
    @FXML
    private Button addCat;
    @FXML
    private TextArea gastoTotal;
    @FXML
    private Double gastoTot = 0.0;
    @FXML
    private Button perfil; // Botón para acceder al perfil
    @FXML
    private Button cerrarS;
    @FXML
    private Label nick;
    @FXML
    private TableView <Charge> visor;
    @FXML
    private TableColumn <Charge, String> gasto; // <From Type, Type gonnabe>
    @FXML
    private TableColumn <Charge, Category> categoria; 
    @FXML
    private TableColumn <Charge, Double> coste;
    @FXML
    private TableColumn <Charge, LocalDate> fecha;
    
    
    private ObservableList<Charge> todosGastos = FXCollections.observableArrayList();
    private List <Charge> todosGas;
    
    String usuario;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            usuario = Acount.getInstance().getLoggedUser().getNickName();
        } catch (AcountDAOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nick.setText(usuario);
        
        //Añadiendo gastos
        
                     try {
            todosGas = (Acount.getInstance().getUserCharges());
        } catch (AcountDAOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PRINCIPALController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

       /*visor.setCellFactory(new Callback<TableView<Charge>, TableCell<Charge>>(0,0) {
            @Override
            public ListCell<Charge> call(TableView<Charge> param) {
                return new ListCell<Charge>() {
                    @Override
                    protected void updateItem(Charge item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        } else {
                            setText(null);
                        }
                    
                        };
            }
                        }              
        });*/
       
        getGastoTotal();
    }
    
    @FXML
    public void getGastoTotal () {
        /*gastoTot = item.doubleValue() + gastoTot;*/
        gastoTot++;
      gastoTotal.setText(gastoTot.toString());
        
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

    
    public void addGasto(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Gasto.fxml"));
        /*stage = new Stage();*/
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Añadir gasto");
        stage.show();
    }
    
    @FXML
    private void irAddCategoria(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
       /* stage = new Stage();*/
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Añadir categoría");
        stage.show();
    }

}
