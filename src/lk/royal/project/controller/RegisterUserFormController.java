package lk.royal.project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.royal.project.bo.BOFactory;
import lk.royal.project.bo.BOType;
import lk.royal.project.bo.custom.impl.LoginBOImpl;
import lk.royal.project.bo.custom.impl.RegistrationBOImpl;
import lk.royal.project.dto.LoginDTO;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterUserFormController {

    public AnchorPane root;
    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    public JFXButton btnRegister;

    @FXML
    public Hyperlink hprLogin;


    LoginBOImpl bo = BOFactory.getInstance().getBO(BOType.LOGIN);
    @FXML
    void hprLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/royal/project/view/LoginForm.fxml"))));
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        if(Pattern.compile("^[A-z|1-9]{1,}$").matcher(txtPassword.getText().trim()).matches()){
            txtPassword.setStyle("-fx-border-color: #0fbcf9; -fx-border-width: 3 ");
            btnRegister.requestFocus();
            btnRegistrationOnAction(event);
        }else {
            txtPassword.setStyle("-fx-border-color: #f53b57; -fx-border-width: 3 ");
            txtPassword.requestFocus();
        }
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        if (Pattern.compile("^[A-z|1-9| ]{1,}$").matcher(txtUserName.getText().trim()).matches()) {
            txtUserName.setStyle("-fx-border-color: #0fbcf9;-fx-border-width: 3  ");
            txtPassword.requestFocus();
        } else {
            txtUserName.setStyle("-fx-border-color: #f53b57;-fx-border-width: 3  ");
            txtUserName.requestFocus();
        }
    }
    @FXML
    public void btnRegistrationOnAction(ActionEvent actionEvent) {
        try {
            String userName = txtUserName.getText().trim();
            String pw = txtPassword.getText().trim();

            if (userName.length() > 0 && pw.length()>0){
                boolean b = bo.saveLogin(new LoginDTO(userName, pw));
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered! Login!", ButtonType.OK).showAndWait();
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/royal/project/view/LoginForm.fxml"))));
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "UserName Already Exists!!", ButtonType.OK).show();
        }
    }
}
