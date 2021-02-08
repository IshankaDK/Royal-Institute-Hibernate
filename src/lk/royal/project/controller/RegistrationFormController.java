package lk.royal.project.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.royal.project.bo.BOFactory;
import lk.royal.project.bo.BOType;
import lk.royal.project.bo.custom.impl.CourseBOImpl;
import lk.royal.project.bo.custom.impl.RegistrationBOImpl;
import lk.royal.project.bo.custom.impl.StudentBOImpl;
import lk.royal.project.dto.CourseDTO;
import lk.royal.project.dto.RegistrationDTO;
import lk.royal.project.dto.StudentDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class RegistrationFormController {

    @FXML
    public AnchorPane main;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtDob;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtCourseFee;

    @FXML
    private JFXTextField txtCourseDuration;

    @FXML
    private JFXTextField txtRegId;

    @FXML
    public JFXDatePicker txtRegDate;

    @FXML
    private JFXTextField txtRegFee;

    @FXML
    private TextField txtSearch;

    @FXML
    public JFXComboBox<String> cmbCourseCode;

    @FXML
    public JFXTextField txtCourseName;

    RegistrationBOImpl bo = BOFactory.getInstance().getBO(BOType.REGISTRATION);
    CourseBOImpl courseBO = BOFactory.getInstance().getBO(BOType.COURSE);
    StudentBOImpl studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);


    public void initialize() {
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtGender.setDisable(true);
        txtDob.setDisable(true);
        cmbCourseCode.setDisable(true);
        txtCourseFee.setDisable(true);
        txtCourseDuration.setDisable(true);
        txtCourseName.setDisable(true);
        txtRegId.setDisable(true);
        txtRegDate.setDisable(true);
        txtRegFee.setDisable(true);

        loadCourse();
    }

    private void loadCourse() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<CourseDTO> list = new ArrayList<>();
        try {
            list = (ArrayList<CourseDTO>) courseBO.getAllCourse();
            for (CourseDTO dto : list) {
                observableList.add(dto.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbCourseCode.setItems(observableList);
    }

    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtGender.setDisable(false);
        txtDob.setDisable(false);
        cmbCourseCode.setDisable(false);
        txtCourseFee.setDisable(false);
        txtCourseDuration.setDisable(false);
        txtCourseName.setDisable(false);
        txtRegId.setDisable(false);
        txtRegDate.setDisable(false);
        txtRegFee.setDisable(false);
        txtRegDate.setValue(LocalDate.now());
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtGender.clear();
        txtDob.clear();
//        cmbCourseCode.setValue();
        txtCourseFee.clear();
        txtCourseDuration.clear();
        txtCourseName.clear();
        txtRegFee.clear();

        try {
            txtRegId.setText(bo.newRegID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String regId = txtRegId.getText();
        String regDate = txtRegDate.getValue().toString();
        double fee = Double.parseDouble(txtRegFee.getText());
        StudentDTO studentDTO = new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(), txtDob.getText(), txtGender.getText());
        CourseDTO courseDTO = new CourseDTO(cmbCourseCode.getValue(), txtCourseName.getText(), Double.parseDouble(txtCourseFee.getText()), txtCourseDuration.getText());

        try {
            boolean flag = bo.addRegistration(new RegistrationDTO(regId, regDate, fee, studentDTO, courseDTO));
            if (flag) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Saved!", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
        }
        btnNewOnAction(event);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String search = txtSearch.getText().trim();
        btnNewOnAction(event);
        try {
            RegistrationDTO registration = bo.getRegistration(search);
            txtRegId.setText(registration.getRegId());
            txtRegDate.setValue(LocalDate.parse(registration.getRedDate()));
            txtRegFee.setText(String.valueOf(registration.getRegFee()));
            txtId.setText(registration.getStudentDTO().getId());
            txtName.setText(registration.getStudentDTO().getName());
            txtAddress.setText(registration.getStudentDTO().getAddress());
            txtContact.setText(registration.getStudentDTO().getContact());
            txtDob.setText(registration.getStudentDTO().getDob());
            txtGender.setText(registration.getStudentDTO().getGender());
            cmbCourseCode.setValue(registration.getCourseDTO().getCode());
            txtCourseName.setText(registration.getCourseDTO().getCourseName());
            txtCourseFee.setText(String.valueOf(registration.getCourseDTO().getFee()));
            txtCourseDuration.setText(registration.getCourseDTO().getDuration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbCourseCodeOnAction(ActionEvent event) {
        try {
            CourseDTO dto = courseBO.getCourse(String.valueOf(cmbCourseCode.getValue()));
            if (dto != null) {
                txtCourseName.setText(dto.getCourseName());
                txtCourseFee.setText(String.valueOf(dto.getFee()));
                txtCourseDuration.setText(dto.getDuration());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void txtIDOnAction(ActionEvent event) {
        try {
            StudentDTO dto = studentBO.getStudent(txtId.getText());
            if (dto != null) {
                txtId.setText(dto.getId());
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtContact.setText(dto.getContact());
                txtDob.setText(dto.getDob());
                txtGender.setText(dto.getGender());
            }
        } catch (Exception e) {
            ButtonType ok = new ButtonType("OK",
                    ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO",
                    ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Student not found!, Add Student ", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                this.main.getChildren().clear();
                try {
                    this.main.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/royal/project/view/StudentForm.fxml")));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        btnSearchOnAction(actionEvent);
    }

    public void txtRegFeeOnAction(ActionEvent actionEvent) {
    }
}
