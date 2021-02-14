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
import javafx.scene.paint.Paint;
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
import java.util.regex.Pattern;

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
        txtCourseId.setText("CT0");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        if (txtCourseId.getText().trim().length() >0 && txtCourseName.getText().trim().length() > 0 && txtFee.getText().trim().length() > 0 && txtDuration.getText().length() >0) {
            if (btnSave.getText().equals("Save")) {
                try {
                    String code = txtCourseId.getText().trim();
                    String name = txtCourseName.getText().trim();
                    double fee = Double.parseDouble(txtFee.getText());
                    String duration = txtDuration.getText();

                    boolean flag = courseBO.addCourse(new CourseDTO(code, name, fee, duration));
                    if (flag) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Course Saved!", ButtonType.OK).show();
                        btnNewOnAction(event);
                    }
                }catch (NumberFormatException e){
                    new Alert(Alert.AlertType.ERROR,"Enter Fee Correctly!",ButtonType.OK).show();
                    txtFee.requestFocus();
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
                }
            } else {
                CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();
                try {
                    boolean b = courseBO.updateCourse(new CourseDTO(selectedItem.getCode(), txtCourseName.getText(),Double.parseDouble(txtFee.getText()),txtDuration.getText()));
                    if (b) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Course Updated!", ButtonType.OK).show();
                        btnNewOnAction(event);
                    }
                }catch (NumberFormatException e){
                    new Alert(Alert.AlertType.ERROR,"Enter Fee Correctly!",ButtonType.OK).show();
                    txtFee.requestFocus();
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
                }
            }
            loadAllCourse();
        }else {
            new Alert(Alert.AlertType.ERROR,"Fields empty!",ButtonType.OK).show();
        }
    }

    @FXML
    void txtCourseIdOnAction(ActionEvent event) {
        if(Pattern.compile("^(CT)[0-9]{1,}$").matcher(txtCourseId.getText().trim()).matches()){
            txtCourseId.setFocusColor(Paint.valueOf("skyblue"));
            txtCourseName.requestFocus();
        }else {
            txtCourseId.setFocusColor(Paint.valueOf("red"));
            txtCourseId.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? ", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            CourseTM selectedItem = tblCourse.getSelectionModel().getSelectedItem();

            try {
                boolean b = courseBO.deleteCourse(selectedItem.getCode());
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Deleted!",ButtonType.OK).show();
                    tblCourse.getItems().remove(selectedItem);
                    tblCourse.getSelectionModel().clearSelection();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
            }
        }
    }

    public void txtFeeOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[0-9]{1,}$").matcher(txtFee.getText().trim()).matches()){
            txtFee.setFocusColor(Paint.valueOf("skyblue"));
            txtDuration.requestFocus();
        }else {
            txtFee.setFocusColor(Paint.valueOf("red"));
            txtFee.requestFocus();
        }
    }

    public void txtCourseNameOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[A-z| ]{1,}$").matcher(txtCourseName.getText().trim()).matches()){
            txtCourseName.setFocusColor(Paint.valueOf("skyblue"));
            txtFee.requestFocus();
        }else {
            txtCourseName.setFocusColor(Paint.valueOf("red"));
            txtCourseName.requestFocus();
        }
    }

    public void txtDurationOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[0-9 |A-z| ]{1,}$").matcher(txtDuration.getText().trim()).matches()){
            txtDuration.setFocusColor(Paint.valueOf("skyblue"));
            btnSaveOnAction(actionEvent);
            txtCourseId.requestFocus();
        }else {
            txtDuration.setFocusColor(Paint.valueOf("red"));
            txtDuration.requestFocus();
        }
    }
}
