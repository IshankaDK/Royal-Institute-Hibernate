package lk.institute.royal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class CourseFormController {
    public AnchorPane main;
    @FXML
    private TableView<?> tblCourse;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colOperation;

    @FXML
    private JFXTextField txtCourseId;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtCourseName;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    void btnNewOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void txtCourseIdOnAction(ActionEvent event) {

    }
}
