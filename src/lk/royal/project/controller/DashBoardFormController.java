package lk.royal.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardFormController {
    public AnchorPane root;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane main;

    public void initialize() throws IOException {
        lblDate.setText(String.valueOf(LocalDate.now()));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        initUI("DefaultForm.fxml");
    }

    @FXML
    void btnCoursesOnAction(ActionEvent event) throws IOException {
        initUI("CourseForm.fxml");
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Course Form");
    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        initUI("DefaultForm.fxml");
    }

    @FXML
    void btnRegistrationOnAction(ActionEvent event) throws IOException {
        initUI("RegistrationForm.fxml");
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Registration Form");
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        initUI("SettingForm.fxml");
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Setting Form");
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
       initUI("StudentForm.fxml");
       Stage stage = (Stage) root.getScene().getWindow();
       stage.setTitle("Student Form");
    }
    private void initUI(String location) throws IOException {
        this.main.getChildren().clear();
        this.main.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/royal/view/" +location)));
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
    }
}
