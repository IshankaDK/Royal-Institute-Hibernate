package lk.institute.royal.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public AnchorPane root;
    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField pswPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Hyperlink hprRegister;

    @FXML
    private ImageView imgEye;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("DashBoard Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/institute/royal/view/DashBoardForm.fxml"))));

    }

    @FXML
    void hprRegisterOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Register Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/institute/royal/view/RegisterUserForm.fxml"))));

    }

    @FXML
    void ingEyeOnAction(MouseEvent event) {

    }

    @FXML
    void pswPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }
}
