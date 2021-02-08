package lk.royal.project.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.royal.project.bo.BOFactory;
import lk.royal.project.bo.BOType;
import lk.royal.project.bo.custom.impl.CourseBOImpl;
import lk.royal.project.bo.custom.impl.ReportBOImpl;
import lk.royal.project.bo.custom.impl.StudentBOImpl;
import lk.royal.project.dao.DAOFactory;
import lk.royal.project.dao.DAOType;
import lk.royal.project.dao.custom.impl.QueryDAOImpl;
import lk.royal.project.dto.CourseDTO;
import lk.royal.project.dto.StudentDTO;
import lk.royal.project.entity.Student;
import lk.royal.project.model.StudentTM;

import java.util.ArrayList;
import java.util.List;

public class ReportFormController {
    public TableView<StudentTM> tblStudent;
    public JFXComboBox cmbCourseId;


    CourseBOImpl courseBO = BOFactory.getInstance().getBO(BOType.COURSE);
    ReportBOImpl bo = BOFactory.getInstance().getBO(BOType.REPORT);

    public void initialize() {
        loadCourse();
    }
    public void cmbCourseIdOnAction(ActionEvent actionEvent) {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblStudent.getItems().clear();
        List<StudentDTO> allStudents = null;
        List<StudentTM> tms = new ArrayList<>();
        try {
            allStudents = bo.getCourseWise(cmbCourseId.getValue().toString());
            for (StudentDTO allStudent : allStudents) {
                tms.add(new StudentTM(allStudent.getId(), allStudent.getName(), allStudent.getAddress(), allStudent.getContact(), allStudent.getDob(), allStudent.getGender()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<StudentTM> students = FXCollections.observableArrayList(tms);
        tblStudent.setItems(students);

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
        cmbCourseId.setItems(observableList);
    }
}
