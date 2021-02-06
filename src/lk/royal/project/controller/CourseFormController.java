package lk.royal.project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.royal.project.bo.BOFactory;
import lk.royal.project.bo.BOType;
import lk.royal.project.bo.custom.impl.CourseBOImpl;
import lk.royal.project.bo.custom.impl.StudentBOImpl;
import lk.royal.project.dto.CourseDTO;
import lk.royal.project.dto.StudentDTO;
import lk.royal.project.model.CourseTM;
import lk.royal.project.model.StudentTM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseFormController {
    public AnchorPane main;

    public JFXButton btnDelete;

    @FXML
    private TableView<CourseTM> tblCourse;

    @FXML
    private JFXTextField txtCourseId;

    @FXML
    private JFXButton btnSave;


    @FXML
    public JFXTextField txtFee;

    @FXML
    private JFXTextField txtCourseName;

    @FXML
    private JFXTextField txtDuration;
    CourseBOImpl courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    public void initialize() {
        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));

        txtCourseId.setDisable(true);
        txtCourseName.setDisable(true);
        txtDuration.setDisable(true);
        txtFee.setDisable(true);

        loadAllCourse();

        tblCourse.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM newValue) {
                CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    txtCourseId.clear();
                    txtCourseName.clear();
                    txtFee.clear();
                    txtDuration.clear();
                    return;
                }
                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtCourseId.setDisable(true);
                txtCourseName.setDisable(false);
                txtFee.setDisable(false);
                txtDuration.setDisable(false);
                txtCourseId.setText(selectedItem.getCode());
                txtCourseName.setText(selectedItem.getCourseName());
                txtFee.setText(String.valueOf(selectedItem.getFee()));
                txtDuration.setText(selectedItem.getDuration());
            }
        });
    }

    private void loadAllCourse() {
        tblCourse.getItems().clear();
        List<CourseDTO> allCourses = new ArrayList<>();
        List<CourseTM> tms = new ArrayList<>();
        try {
            allCourses = courseBO.getAllCourse();
            for (CourseDTO allCourse : allCourses) {
                tms.add(new CourseTM(allCourse.getCode(),allCourse.getCourseName(),allCourse.getFee(),allCourse.getDuration()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<CourseTM> course = FXCollections.observableArrayList(tms);
        tblCourse.setItems(course);
    }

    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtCourseId.clear();
        txtCourseName.clear();
        txtFee.clear();
        txtDuration.clear();
        tblCourse.getSelectionModel().clearSelection();
        txtCourseId.setDisable(false);
        txtCourseName.setDisable(false);
        txtFee.setDisable(false);
        txtDuration.setDisable(false);
        txtCourseId.setText("CT");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = txtCourseId.getText().trim();
        String name = txtCourseName.getText();
        double fee = Double.parseDouble(txtFee.getText());
        String duration = txtDuration.getText();


        if (code.trim().length() == 0 || name.trim().length() == 0 || fee == 0 || duration.trim().length() == 0 ) {
            new Alert(Alert.AlertType.ERROR, " Fields can not be empty", ButtonType.OK).show();
        }

        if (btnSave.getText().equals("Save")) {
            try {
                boolean flag = courseBO.addCourse(new CourseDTO(code, name, fee, duration));
                if (flag) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Course Saved!", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
            }
              btnNewOnAction(event);
        } else {
            CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();
            try {
                boolean b = courseBO.updateCourse(new CourseDTO(selectedItem.getCode(), txtCourseName.getText(),Double.parseDouble(txtFee.getText()),txtDuration.getText()));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Course Updated!", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
            }
            btnNewOnAction(event);
        }
        loadAllCourse();
    }

    @FXML
    void txtCourseIdOnAction(ActionEvent event) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? ", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();

            try {
                courseBO.deleteCourse(selectedItem.getCode());
                tblCourse.getItems().remove(selectedItem);
                tblCourse.getSelectionModel().clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
            }
        }
    }
}
