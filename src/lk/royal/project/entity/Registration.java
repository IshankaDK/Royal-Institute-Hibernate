package lk.royal.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Registration implements SuperEntity{
    @Id
    private
    String regId;
    private String redDate;
    private double regFee;
    @OneToOne
    private
    Student student;
    @OneToOne
    private
    Course course;

    public Registration() {
    }

    public Registration(String regId, String redDate, double regFee, Student student, Course course) {
        this.setRegId(regId);
        this.setRedDate(redDate);
        this.setRegFee(regFee);
        this.setStudent(student);
        this.setCourse(course);
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRedDate() {
        return redDate;
    }

    public void setRedDate(String redDate) {
        this.redDate = redDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regId='" + regId + '\'' +
                ", redDate='" + redDate + '\'' +
                ", regFee=" + regFee +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
