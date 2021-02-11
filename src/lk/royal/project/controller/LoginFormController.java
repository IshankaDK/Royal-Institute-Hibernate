package lk.royal.project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.royal.project.bo.BOFactory;
import lk.royal.project.bo.BOType;
import lk.royal.project.bo.custom.impl.LoginBOImpl;
import lk.royal.project.dto.LoginDTO;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFormController {

    public AnchorPane root;
    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField pswPassword;

    @FXML
    public TextField txtPassword;
    @FXML
    private JFXButton btnLogin;

    @FXML
    private Hyperlink hprRegister;

    @FXML
    public JFXCheckBox checkBox;


    LoginBOImpl bo = BOFactory.getInstance().getBO(BOType.LOGIN);

    public void initialize() {
        txtPassword.setVisible(false);

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) ->{
            if (checkBox.isSelected()){
                txtPassword.setText(pswPassword.getText());
                txtPassword.setVisible(true);
                pswPassword.setVisible(false);
                return;
            }
            pswPassword.setText(txtPassword.getText());
            pswPassword.setVisible(true);
            txtPassword.setVisible(false);
        } );
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

        try {
            String userName = txtUserName.getText().trim();
            if (userName.length() > 0 && pswPassword.getText().trim().length() > 0) {
                LoginDTO login = bo.getLogin(userName);
                if (login.getPassword().equals(pswPassword.getText().trim())) {
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setTitle("DashBoard Form");
                    stage.centerOnScreen();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/royal/project/view/DashBoardForm.fxml"))));
                } else {
                    new Alert(Alert.AlertType.ERROR, "User Name Password does not match!", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Enter UserName and Password", ButtonType.OK).show();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Login Failed!, Try Again!", ButtonType.OK).show();
        }
    }

    @FXML
    void hprRegisterOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Register Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/royal/project/view/RegisterUserForm.fxml"))));

    }


    @FXML
    void pswPasswordOnAction(ActionEvent event) throws IOException {
        if (txtUserName.getText().trim().length() > 0) {
            txtUserName.setStyle("-fx-border-color: #0fbcf9;-fx-border-width: 3  ");
            btnLoginOnAction(event);
        } else {
            txtUserName.setStyle("-fx-border-color: #f53b57;-fx-border-width: 3  ");
            txtUserName.requestFocus();
        }
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        if (txtUserName.getText().trim().length() > 0) {
            txtUserName.setStyle("-fx-border-color: #0fbcf9;-fx-border-width: 3  ");
            pswPassword.requestFocus();
        } else {
            txtUserName.setStyle("-fx-border-color: #f53b57;-fx-border-width: 3  ");
            txtUserName.requestFocus();
        }
    }

}
