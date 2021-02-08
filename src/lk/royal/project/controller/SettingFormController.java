package lk.royal.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.royal.project.bo.BOFactory;
import lk.royal.project.bo.BOType;
import lk.royal.project.bo.custom.impl.LoginBOImpl;
import lk.royal.project.dto.LoginDTO;

import java.util.regex.Pattern;

public class SettingFormController {

    public AnchorPane main;
    public TextField txtUserName;
    public TextField txtOldPassword;
    public TextField txtNewPassword;

    LoginBOImpl bo = BOFactory.getInstance().getBO(BOType.LOGIN);

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            String userName = txtUserName.getText().trim();
            String old = txtOldPassword.getText().trim();
            String newPass = txtNewPassword.getText().trim();

            if(userName.length()>0 && old.length()>0 && newPass.length()>0){
                LoginDTO login = bo.getLogin(userName);
                if(login.getPassword().equals(old)){
                    if (old.equals(newPass)){
                        new Alert(Alert.AlertType.WARNING,"New Password can not be Old Password!",ButtonType.OK).show();
                        return;
                    }
                    boolean b = bo.updatePassword(new LoginDTO(userName, newPass));
                    if (b){
                        new Alert(Alert.AlertType.CONFIRMATION,"Password Changed!", ButtonType.OK).show();
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING,"Invalid Username or password",ButtonType.OK).show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"Fields Empty",ButtonType.OK).show();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed",ButtonType.OK).show();
        }
    }

    @FXML
    void txtNewPasswordOnAction(ActionEvent event) {
        if(Pattern.compile("^[A-z|1-9]{1,}$").matcher(txtNewPassword.getText().trim()).matches()){
            txtNewPassword.setStyle("-fx-border-color: #0fbcf9; -fx-border-width: 3 ");
            btnSaveOnAction(event);
        }else {
            txtNewPassword.setStyle("-fx-border-color: #f53b57; -fx-border-width: 3 ");
            txtNewPassword.requestFocus();
        }
    }

    @FXML
    void txtOldPasswordOnAction(ActionEvent event) {
        if(Pattern.compile("^[A-z|1-9]{1,}$").matcher(txtOldPassword.getText().trim()).matches()){
            txtOldPassword.setStyle("-fx-border-color: #0fbcf9; -fx-border-width: 3 ");
            txtNewPassword.requestFocus();
        }else {
            txtOldPassword.setStyle("-fx-border-color: #f53b57; -fx-border-width: 3 ");
            txtOldPassword.requestFocus();
        }
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        if (Pattern.compile("^[A-z|1-9| ]{1,}$").matcher(txtUserName.getText().trim()).matches()) {
            txtUserName.setStyle("-fx-border-color: #0fbcf9;-fx-border-width: 3  ");
            txtOldPassword.requestFocus();
        } else {
            txtUserName.setStyle("-fx-border-color: #f53b57;-fx-border-width: 3  ");
            txtUserName.requestFocus();
        }
    }
}
