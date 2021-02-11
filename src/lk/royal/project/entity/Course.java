package lk.royal.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course implements SuperEntity {
    @Id
    private String code;
    private String courseName;
    private double fee;
    private String duration;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Registration> registration;

    public Course(String code, String courseName, double fee, String duration, List<Registration> registration) {
        this.setCode(code);
        this.setCourseName(courseName);
        this.setFee(fee);
        this.setDuration(duration);
        this.setRegistration(registration);
    }

    public Course(String code, String courseName, double fee, String duration) {
        this.setCode(code);
        this.setCourseName(courseName);
        this.setFee(fee);
        this.setDuration(duration);
    }

    public Course() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "Course{" +
                "code='" + getCode() + '\'' +
                ", courseName='" + getCourseName() + '\'' +
                ", fee=" + getFee() +
                ", duration='" + getDuration() + '\'' +
                ", registration=" + getRegistration() +
                '}';
    }

    public List<Registration> getRegistration() {
        return registration;
    }

    public void setRegistration(List<Registration> registration) {
        this.registration = registration;
    }
}
