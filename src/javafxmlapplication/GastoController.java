/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import javafx.util.StringConverter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Acount;
import model.AcountDAOException;
import model.Category;


public class GastoController implements Initializable{
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    

    @FXML
    private Button volver;
    @FXML
    private Button aceptar;
    @FXML
    private Button nuevaCategoria;
    @FXML
    private Button subirFactura;
    @FXML
    private ComboBox categoria;
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descripcion;
    @FXML
    private TextField coste;
    @FXML
    private TextField cantidad;
    @FXML
    private DatePicker fecha;
    @FXML
    private List<Category> todasCategorias;
    @FXML
    private ImageView marco;
    
    private FileChooser fileChooser;
    private Image imagenCargada = null;
    
   /* @FXML
    private Label descifrar;
    
    Category cat1;*/
   
    /* Para usar los métodos: Acount.getInstance().*metodoquequieras()*; */
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        try {
        todasCategorias = (Acount.getInstance().getUserCategories());
        /*Category cat2 = todasCategorias.get(0);
        String adaptado = cat2.getName();
        System.out.println(adaptado);*/
        }
        catch (AcountDAOException error) {
             Alert errorUs = new Alert(Alert.AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("No hay categorías de gastos registradas");
                errorUs.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(GastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        categoria.getItems().addAll(todasCategorias);
        
        /*Para que se vean los nombres al DESPLEGAR el combobox*/
        categoria.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
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
        
        // Para que se vea el nombre de la selección*/
        categoria.setButtonCell(new ListCell<Category>() {
            @Override
            protected void updateItem(Category item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                } else {
                    setText(null);
                }
            }
        });
        
       /* categoria.setOnAction(this::descif);*/
    }   
    
    
    @FXML
    private void irAddCategoria(ActionEvent event) throws IOException {

        
        root = FXMLLoader.load(getClass().getResource("Categoria.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Añadir categoría");
        stage.show();
    }
    
    
    @FXML
    public void addCharge(ActionEvent event) throws IOException, NumberFormatException{
        try {
            String Titulo = titulo.getText();
        int cantidadI = Integer.parseInt(cantidad.getText());
        double costeD = Double.parseDouble(coste.getText());
        LocalDate Fecha = fecha.getValue();
        Category cate = (Category)categoria.getValue();
            if (Titulo.isEmpty()||cate.equals(null)||Fecha.equals(null)) {
            Alert error = new Alert (Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Los campos obligatorios no pueden estar vacíos");
            error.showAndWait();
            }
            else {if (costeD<0 || cantidadI<0){
              Alert error = new Alert (Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Debes insertar un valor positivo en los datos numéricos");
            error.showAndWait();
            }
            else {
            boolean gast = Acount.getInstance().registerCharge(Titulo, descripcion.getText(), 
                    costeD, cantidadI, imagenCargada, Fecha, cate);
                                                                /*Añadir factura opcional*/
        
            if (gast) {
        root = FXMLLoader.load(getClass().getResource("VerGastos.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Gastos");
        stage.show();
            }
    }
    }
    }
    catch (AcountDAOException error){
    
    Alert errorUs = new Alert(Alert.AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("Campos necesarios no rellenados.");
                errorUs.showAndWait();
                }
        
     catch (NumberFormatException ex) {
        Alert excN = new Alert(Alert.AlertType.ERROR);
                excN.setTitle("Error");
                excN.setHeaderText("Formato de número no permitido");
                excN.showAndWait();
                } 
     }
    
    
    /*FXML public void subirFactura */
    
 /*   NO HACE FALTA, ERAN PROBATURAS
    
    @FXML
    public void descif (Event event) {
        
        cat1 = (Category) categoria.getValue();
        String nameCat = cat1.getName();
        descifrar.setText(nameCat);
        Category selectedCategory = (Category) categoria.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            categoria.setPromptText(selectedCategory.getName());
        }
    }*/
     
    @FXML
    private void cargarImagen(ActionEvent event) throws IOException {
     FileChooser fileChooser = new FileChooser();
     fileChooser.setTitle("Seleccionar Archivo");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Todos los Archivos", "*.*")
            );
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
               Image image = new Image(file.toURI().toString());  
               imagenCargada = image;
               marco.setImage(imagenCargada);
            }   
    }
    
    @FXML
    public void actualizar() throws IOException {

        
        try {
        todasCategorias = (Acount.getInstance().getUserCategories());
        /*Category cat2 = todasCategorias.get(0);
        String adaptado = cat2.getName();
        System.out.println(adaptado);*/
        }
        catch (AcountDAOException error) {
             Alert errorUs = new Alert(Alert.AlertType.ERROR);
                errorUs.setTitle("Error");
                errorUs.setHeaderText("No hay categorías de gastos registradas");
                errorUs.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(GastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        categoria.getItems().clear();
        categoria.getItems().addAll(todasCategorias);
        
        /*Para que se vean los nombres al DESPLEGAR el combobox*/
        categoria.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
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
        
        // Para que se vea el nombre de la selección*/
        categoria.setButtonCell(new ListCell<Category>() {
            @Override
            protected void updateItem(Category item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                } else {
                    setText(null);
                }
            }
        });
    
    }
    
    @FXML
    public void cancel(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("PRINCIPAL.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        Scene scene = new Scene(root,800,400);
        stage.setScene(scene);
        stage.setTitle("Principal");
        stage.show();
       /* Stage myStage= (Stage) this.volver.getScene().getWindow();*/
       /* myStage.close();/*También se puede hide, no sé la diferencia*/
        
    }
}
/*
private void descif(javafx.event.ActionEvent event) {
        // Implementa la lógica que debe ocurrir cuando se selecciona una categoría
        Category selectedCategory = categoria.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            System.out.println("Selected category: " + selectedCategory.getName());
        }


*/