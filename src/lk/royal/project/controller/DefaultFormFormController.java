package lk.royal.project.controller;

import javafx.scene.control.Label;
import lk.royal.project.bo.BOFactory;
import lk.royal.project.bo.BOType;
import lk.royal.project.bo.custom.impl.DefaultBOImpl;

public class DefaultFormFormController {

    public Label lblTotStudent;
    public Label lblTotCourse;


    DefaultBOImpl bo = BOFactory.getInstance().getBO(BOType.DEFAULT);

    public void initialize(){
        getTotCourse();
        getTotStudent();
    }

    void getTotStudent(){
        try {
            lblTotStudent.setText(String.valueOf(bo.getTotalStudent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void getTotCourse(){
        try {
            lblTotCourse.setText(String.valueOf(bo.getTotalCourse()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
