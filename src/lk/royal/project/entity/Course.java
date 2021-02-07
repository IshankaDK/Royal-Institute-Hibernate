package lk.royal.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Course implements SuperEntity {
    @Id
    private String code;
    private String courseName;
    private double fee;
    private String duration;
    @OneToOne(mappedBy = "course")
    private Registration registration;

    public Course(String code, String courseName, double fee, String duration, Registration registration) {
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

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                ", fee=" + fee +
                ", duration='" + duration + '\'' +
                ", registration=" + registration +
                '}';
    }
}
