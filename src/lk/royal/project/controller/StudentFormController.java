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
import lk.royal.project.bo.custom.impl.StudentBOImpl;
import lk.royal.project.dto.StudentDTO;
import lk.royal.project.model.StudentTM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentFormController {

    @FXML
    private AnchorPane main;

    @FXML
    private TableView<StudentTM> tblStudent;


    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtDob;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;


    StudentBOImpl studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);

    public void initialize() {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtGender.setDisable(true);
        txtDob.setDisable(true);

        loadAllStudent();

        tblStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>() {
            @Override
            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {
                StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    txtId.clear();
                    txtName.clear();
                    txtAddress.clear();
                    txtContact.clear();
                    txtGender.clear();
                    txtDob.clear();
                    return;
                }
                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtId.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtContact.setDisable(false);
                txtGender.setDisable(false);
                txtDob.setDisable(false);
                txtId.setText(selectedItem.getId());
                txtName.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());
                txtContact.setText(selectedItem.getContact());
                txtGender.setText(selectedItem.getGender());
                txtDob.setText(selectedItem.getDob());
            }
        });
    }

    private void loadAllStudent() {
        tblStudent.getItems().clear();
        List<StudentDTO> allStudents = null;
        List<StudentTM> tms = new ArrayList<>();
        try {
            allStudents = studentBO.getAllStudent();
            for (StudentDTO allStudent : allStudents) {
                tms.add(new StudentTM(allStudent.getId(), allStudent.getName(), allStudent.getAddress(), allStudent.getContact(), allStudent.getDob(), allStudent.getGender()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<StudentTM> students = FXCollections.observableArrayList(tms);
        tblStudent.setItems(students);
    }


    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtGender.clear();
        txtDob.clear();
        tblStudent.getSelectionModel().clearSelection();
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtGender.setDisable(false);
        txtDob.setDisable(false);
        try {
            String s = studentBO.newStudentId();
            txtId.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText().trim();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String dob = txtDob.getText();
        String gender = txtGender.getText();

        if (name.trim().length() == 0 || address.trim().length() == 0 || contact.trim().length() == 0 || dob.trim().length() == 0 || gender.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, " Fields can not be empty", ButtonType.OK).show();
        }

        if (btnSave.getText().equals("Save")) {
            try {
                boolean flag = studentBO.addStudent(new StudentDTO(id, name, address, contact, dob, gender));
                if (flag) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Saved!", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
            }
            btnNewOnAction(event);
        } else {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            try {
                boolean b = studentBO.updateStudent(new StudentDTO(selectedItem.getId(), txtName.getText(), txtAddress.getText(), txtContact.getText(), txtDob.getText(), txtGender.getText()));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Updated!", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
            }
            btnNewOnAction(event);
        }
        loadAllStudent();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? ", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

            try {
                studentBO.deleteStudent(selectedItem.getId());
                tblStudent.getItems().remove(selectedItem);
                tblStudent.getSelectionModel().clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed..!", ButtonType.OK).show();
            }
        }
    }
}
