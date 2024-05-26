package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Acount;
import model.AcountDAOException;

public class REGISTROController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField correo;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contraseña;
    @FXML
    private TextField mostrarcontraseña;
    @FXML
    private Button siguiente;
    @FXML
    private Button atras;
    @FXML
    private Button selecciona;
    @FXML
    private ImageView avatar;
    @FXML
    private ImageView ojoImageView;

    @FXML
    Image image = new Image("default.png");

    private boolean isDisabled;
    private boolean newuser = false;
    String Nombre;
    String Apellido;
    String Correo;
    String Usuario;
    String Contraseña;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        siguiente.setDisable(true);
        mostrarcontraseña.setVisible(false);
        mostrarcontraseña.setManaged(false);
    }

    @FXML
    public void newuser(javafx.event.ActionEvent event) throws IOException {
        Nombre = nombre.getText();
        Apellido = apellido.getText();
        Correo = correo.getText();
        Usuario = usuario.getText();
        Contraseña = contraseña.getText();
        isDisabled = (Nombre.isEmpty() || Correo.isEmpty() || Usuario.isEmpty() || Contraseña.isEmpty() || Apellido.isEmpty());
        siguiente.setDisable(isDisabled);

        try {
            if (isDisabled || Usuario.contains(" ") || Contraseña.length() < 7) {
                if (isDisabled) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("No ha rellenado todos los campos obligatorios * ");
                    error.showAndWait();
                }

                if (Usuario.contains(" ")) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("El usuario no ha de tener espacios");
                    error.showAndWait();
                    usuario.clear();
                    siguiente.setDisable(true);
                }

                if (Contraseña.length() < 7) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText("La contraseña ha de tener mínimo 7 carácteres");
                    error.showAndWait();
                    contraseña.clear();
                    siguiente.setDisable(true);
                }
            } else {
                LocalDate fecha = LocalDate.now();

                newuser = Acount.getInstance().registerUser(Nombre,
                        Apellido, Correo, Usuario, Contraseña, image, fecha);
                System.out.println(Usuario + " registrado!");

                if (newuser) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Usuario nuevo registrado");
                    alert.setHeaderText("Se ha registrado correctamente el usuario " + Usuario + ". Pulse 'Aceptar' para ir a la ventana de Autenticación.");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        irAutenticacion(event);
                    }
                }
            }
        } catch (AcountDAOException error) {
            Alert errorUs = new Alert(AlertType.ERROR);
            errorUs.setTitle("Error");
            errorUs.setHeaderText("Nombre de usuario ya registrado");
            errorUs.showAndWait();
            usuario.clear();
        }
    }

    @FXML
    public void SeleccionarAvatar(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Avatares.fxml"));
        root = loader.load();
        stage = new Stage();
        AvataresController avatarescontroller = loader.getController();
        avatarescontroller.introducirImagen(image);

        stage.setResizable(false);
        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.setTitle("Foto de perfil");
        stage.showAndWait();
        if (avatarescontroller.isISelected()) {
            image = avatarescontroller.getImage();
            avatar.setImage(image);
        }
    }

    @FXML
    public void keyReleasedProperty() {
        Nombre = nombre.getText();
        Apellido = apellido.getText();
        Correo = correo.getText();
        Usuario = usuario.getText();
        Contraseña = contraseña.getText();
        isDisabled = (Nombre.isEmpty() || Correo.isEmpty() || Usuario.isEmpty() || Contraseña.isEmpty() || Apellido.isEmpty());
        siguiente.setDisable(isDisabled);
    }

    @FXML
    public void volver(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.setTitle("BalanceWatcher");
        stage.show();
    }

    @FXML
    public void irAutenticacion(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Autenticacion.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);
        stage.setTitle("Autenticación");
        stage.show();
    }

    @FXML
    public void mostrarContraseña(MouseEvent event) {
        mostrarcontraseña.setText(contraseña.getText());
        mostrarcontraseña.setVisible(true);
        mostrarcontraseña.setManaged(true);
        contraseña.setVisible(false);
        contraseña.setManaged(false);
    }

    @FXML
    public void ocultarContraseña(MouseEvent event) {
        contraseña.setText(mostrarcontraseña.getText());
        contraseña.setVisible(true);
        contraseña.setManaged(true);
        mostrarcontraseña.setVisible(false);
        mostrarcontraseña.setManaged(false);
    }
}
